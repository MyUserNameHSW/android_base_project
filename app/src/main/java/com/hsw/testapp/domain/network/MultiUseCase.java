package com.hsw.testapp.domain.network;



import com.hsw.testapp.domain.executor.PostExecutionThread;
import com.hsw.testapp.domain.executor.ThreadExecutor;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by hsw
 * on 2018/8/17.
 */
public abstract class MultiUseCase {
    protected final ThreadExecutor threadExecutor;
    protected final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    public MultiUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    public <T> void execute(DisposableObserver<T> observer, Observable<T> observable){
        Preconditions.checkNotNull(observable);
        Preconditions.checkNotNull(observer);
        DisposableObserver disposableObserver = observable
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(observer);
        addDisposable(disposableObserver);
    }

    public void dispose(){
        if (!disposables.isDisposed()){
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable disposable){
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }
}
