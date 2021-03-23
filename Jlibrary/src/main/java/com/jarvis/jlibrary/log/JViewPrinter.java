package com.jarvis.jlibrary.log;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jarvis.jlibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinxiaodong
 * @description：实现log日志显示到UI界面
 * @date 3/22/21
 */
public class JViewPrinter implements JLogPrinter {

    private RecyclerView recyclerView;
    private LogAdapter logAdapter;
    private JViewPrinterProvider viewProvider;

    public JViewPrinter(Activity activity) {
        FrameLayout content = activity.findViewById(android.R.id.content);
        recyclerView = new RecyclerView(activity);
        logAdapter = new LogAdapter(LayoutInflater.from(recyclerView.getContext()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(logAdapter);

        viewProvider = new JViewPrinterProvider(content, recyclerView);
    }

    /**
     * @return
     */
    @NonNull
    public JViewPrinterProvider getViewProvider() {
        return viewProvider;
    }

    @Override
    public void print(@NonNull JLogConfig config, int level, String tag, @NonNull String printString) {
        JLogMo jLogMo = new JLogMo(System.currentTimeMillis(), level, tag, printString);
        logAdapter.addItem(jLogMo);
        recyclerView.smoothScrollToPosition(logAdapter.getItemCount() - 1);
    }


    private static class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {

        private LayoutInflater inflater;

        private List<JLogMo> logs = new ArrayList<>();

        public LogAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        void addItem(JLogMo logMo) {
            logs.add(logMo);
            notifyItemChanged(logs.size() - 1);
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.jlog_item, parent, false);
            return new LogViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            JLogMo jLogMo = logs.get(position);
            int color = getHighlightColor(jLogMo.level);
            holder.messageView.setText(jLogMo.log);
            holder.messageView.setTextColor(color);

            holder.tagView.setText(jLogMo.getFlattened());
            holder.tagView.setTextColor(color);

        }

        @Override
        public int getItemCount() {
            return logs.size();
        }

        /**
         * 根据log级别获取log颜色
         *
         * @param level
         * @return
         */
        public int getHighlightColor(int level) {
            int hightlight;
            switch (level) {
                case JLogType.V:
                    hightlight = 0xffbbbbbb;
                    break;
                case JLogType.D:
                    hightlight = 0xffffffff;
                    break;
                case JLogType.I:
                    hightlight = 0xff6a8759;
                    break;
                case JLogType.W:
                    hightlight = 0xffbbb529;
                    break;
                case JLogType.E:
                    hightlight = 0xffbb6b68;
                    break;
                default:
                    hightlight = 0xffffff00;
                    break;
            }
            return hightlight;

        }
    }


    private static class LogViewHolder extends RecyclerView.ViewHolder {

        TextView tagView;
        TextView messageView;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.tag);
            messageView = itemView.findViewById(R.id.message);
        }
    }
}
