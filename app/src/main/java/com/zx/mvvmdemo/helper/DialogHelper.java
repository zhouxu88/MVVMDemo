package com.zx.mvvmdemo.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by mj
 * on 2017/5/22.
 */

public class DialogHelper {

    public static DialogHelper getInstance() {
        return LoadDialogHolder.instance;
    }

    private static class LoadDialogHolder {
        static DialogHelper instance = new DialogHelper();
    }

    /**
     * 展示加载框
     *
     * @param context context
     * @param msg     加载信息
     */
    public void show(Context context, String msg) {
        close();
        createDialog(context, msg);
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    /**
     * 关闭加载框
     */
    public void close() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * progressDialog
     */
    private ProgressDialog progressDialog;

    /**
     * 创建加载框
     *
     * @param context context
     * @param msg     msg
     */
    private void createDialog(Context context, String msg) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(msg);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                progressDialog = null;
            }
        });
    }
}

