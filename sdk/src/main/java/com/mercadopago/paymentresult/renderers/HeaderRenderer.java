package com.mercadopago.paymentresult.renderers;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercadopago.R;
import com.mercadopago.components.Renderer;
import com.mercadopago.components.RendererFactory;
import com.mercadopago.paymentresult.components.HeaderComponent;
import com.mercadopago.util.CurrenciesUtil;

/**
 * Created by vaserber on 10/20/17.
 */

public class HeaderRenderer extends Renderer<HeaderComponent> {

    private Renderer iconRenderer;

    @Override
    public View render() {

        final View headerView = LayoutInflater.from(context).inflate(R.layout.mpsdk_payment_result_header, null, false);
        final ViewGroup headerContainer = (ViewGroup) headerView.findViewById(R.id.mpsdkPaymentResultContainerHeader);
        final TextView titleTextView = (TextView) headerView.findViewById(R.id.mpsdkHeaderTitle);
        final ViewGroup iconParentViewGroup = (ViewGroup) headerView.findViewById(R.id.iconContainer);
        final TextView labelTextView = (TextView) headerView.findViewById(R.id.mpsdkHeaderLabel);

        headerContainer.setBackgroundColor(ContextCompat.getColor(context, component.getProps().background));
        setText(labelTextView, component.getProps().label);

        renderIcon(iconParentViewGroup);
        renderTitle(titleTextView);

        //TODO: Reimplementar el body height en un componente.
//        renderHeight();

        return headerView;
    }

//    private void renderHeight() {
//        if (component.getProps().height.equals("wrap")) {
//            wrapHeight(headerContainer);
//        } else if (component.getProps().height.equals("stretch")) {
//            stretchHeight(headerContainer);
//        }
//    }

    private void renderIcon(@NonNull final ViewGroup parent) {
        iconRenderer = RendererFactory.create(context, component.getIconComponent());
        View icon = iconRenderer.render();
        parent.addView(icon);
    }

    private void renderTitle(@NonNull final TextView titleTextView) {
        if (component.getProps().amountFormat == null) {
            setText(titleTextView, component.getProps().title);
        } else {
            Spanned formattedTitle = CurrenciesUtil.formatCurrencyInText("<br>",
                    component.getProps().amountFormat.getAmount(),
                    component.getProps().amountFormat.getCurrencyId(),
                    component.getProps().title, false, true);
            titleTextView.setText(formattedTitle);
        }
    }
}
