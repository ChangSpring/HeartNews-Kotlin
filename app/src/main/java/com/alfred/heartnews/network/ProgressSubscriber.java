package com.alfred.heartnews.network;

import android.content.Context;

import rx.Subscriber;

/**
 * Created by alfred on 2018/5/17.
 */

public class ProgressSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private SubscriberOnNextListener mListener;
    private ProgressDialogHandler mHandler;

    public ProgressSubscriber(Context context,SubscriberOnNextListener listener) {
        mContext = context;
        mListener = listener;
        mHandler = new ProgressDialogHandler(context, null, true);
    }

    private void showProgressDialog() {
        if (mHandler != null) {
            mHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mHandler != null) {
            mHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
        }
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
    }

    @Override
    public void onNext(T t) {
        if (mListener != null) {
            mListener.onNext(t);
        }
    }
}
