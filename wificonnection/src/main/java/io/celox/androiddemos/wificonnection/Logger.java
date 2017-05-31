///*
// * Copyright (c) 2017 Martin Pfeffer
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package io.celox.androiddemos.wificonnection;
//
//import android.util.Log;
//
//public class Logger {
//    private String LOG_TAG;
//
//    private Logger(Class clazz) {
//        this.LOG_TAG = clazz.getSimpleName();
//    }
//
//    public static Logger loggerFor(Class clazz) {
//        return new Logger(clazz);
//    }
//
//    public void d(String message) {
//        if (BuildConfig.DEBUG) {
//            Log.d(LOG_TAG, message);
//        }
//    }
//
//    public void d(String message, Throwable throwable) {
//        if (BuildConfig.DEBUG) {
//            Log.d(LOG_TAG, message, throwable);
//        }
//    }
//
//    public void i(String message) {
//        if (BuildConfig.DEBUG) {
//            Log.i(LOG_TAG, message);
//        }
//    }
//
//    public void e(String message) {
//        Log.e(LOG_TAG, message);
//    }
//
//    public void e(String message, Throwable throwable) {
//        Log.e(LOG_TAG, message, throwable);
//    }
//}