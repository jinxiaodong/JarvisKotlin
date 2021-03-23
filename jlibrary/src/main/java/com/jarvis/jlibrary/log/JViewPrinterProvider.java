package com.jarvis.jlibrary.log;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jarvis.jlibrary.extension.DisplayExtensionsKt;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/22/21
 */
public class JViewPrinterProvider {


    public static final String TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW";
    public static final String TAG_LOG_VIEW = "TAG_LOG_VIEW";

    private FrameLayout rootView;
    private View floatingView;
    private boolean isOpen;
    private FrameLayout logView;
    private RecyclerView recyclerView;

    public JViewPrinterProvider(FrameLayout rootView, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.recyclerView = recyclerView;
    }


    public void showFloatingView() {
        if (rootView.findViewWithTag(TAG_FLOATING_VIEW) != null) {
            return;
        }

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.END;
        View floatingView = genFloatinView();
        floatingView.setTag(TAG_FLOATING_VIEW);
        floatingView.setBackgroundColor(Color.BLACK);
        floatingView.setAlpha(0.8f);
        layoutParams.bottomMargin = (int) DisplayExtensionsKt.getDp(100);

//                JDisplayUtil.dp2px(100,recyclerView.getResources());
        rootView.addView(floatingView, layoutParams);
    }

    public void closeFloatingView() {

        rootView.removeView(genFloatinView());
    }


    private View genFloatinView() {
        if (floatingView == null) {
            TextView textView = new TextView(rootView.getContext());
            textView.setOnClickListener(v -> {
                if (!isOpen) {
                    showLogView();
                }
            });
            textView.setText("JLog");
            textView.setTextColor(Color.WHITE);
            floatingView = textView;
        }
        return floatingView;
    }

    private void showLogView() {
        if (rootView.findViewWithTag(TAG_LOG_VIEW) != null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DisplayExtensionsKt.getDp(160));
        layoutParams.gravity = Gravity.BOTTOM;
        View logView = genLogView();
        rootView.addView(logView, layoutParams);
    }


    private View genLogView() {
        if (logView == null) {
            FrameLayout frameLayout = new FrameLayout(rootView.getContext());
            frameLayout.setBackgroundColor(Color.BLACK);
            frameLayout.addView(recyclerView);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.TOP | Gravity.END;
            TextView closeView = new TextView(rootView.getContext());
            closeView.setText("close");
            closeView.setTextColor(Color.WHITE);
            closeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closeLogView();
                }
            });
            frameLayout.addView(closeView, layoutParams);
            frameLayout.setTag(TAG_LOG_VIEW);
            logView = frameLayout;
        }

        return logView;
    }

    private void closeLogView() {
        isOpen = false;
        rootView.removeView(genLogView());

    }

}
