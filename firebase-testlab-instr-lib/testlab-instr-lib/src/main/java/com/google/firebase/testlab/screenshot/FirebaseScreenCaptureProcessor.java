/*
* Copyright 2016 Google Inc.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and 
* limitations under the License.
*/

package com.google.firebase.testlab.screenshot;

import android.support.test.runner.screenshot.BasicScreenCaptureProcessor;

import org.junit.Test;

import java.io.File;
import java.lang.reflect.Method;

/**
 * The {@link android.support.test.runner.screenshot.ScreenCaptureProcessor} to be used when running
 * tests on Firebase Test Lab.
 * <p>
 * <p>This will save the screenshot to a location where Firebase Test Lab can find them.
 */
public class FirebaseScreenCaptureProcessor extends BasicScreenCaptureProcessor {
    public FirebaseScreenCaptureProcessor() {
        mTag = "FirebaseScreenCaptureProcessor";
        mDefaultFilenamePrefix = "firebaseScreenshot";
        mDefaultScreenshotPath = new File("/sdcard/screenshots");
    }

    @Override
    protected String getDefaultFilename() {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            String elementClassName = element.getClassName();
            String elementMethodName = element.getMethodName();

            if (isJunit3Test(elementMethodName) ||
                isJUnit4Test(elementClassName, elementMethodName)) {
                return getFilename(elementClassName + mFileNameDelimiter + elementMethodName);
            }
        }
        return getFilename("UnknownTestClass" + mFileNameDelimiter + "unknownTestMethod");
    }

    /**
     * Returns whether the given method inside the given class is a Junit3 test method.
     */
    private static boolean isJunit3Test(String elementMethodName) {
        return elementMethodName.startsWith("test");
    }

    /**
     * Returns whether the given method inside the given class is a Junit4 test method.
     */
    private static boolean isJUnit4Test(String elementClassName, String elementMethodName) {
        try {
            Class<?> clazz = Class.forName(elementClassName);
            for (Method method : clazz.getMethods()) {
                if (method.getName().equals(elementMethodName) &&
                    method.isAnnotationPresent(Test.class)) {
                    return true;
                }
            }
        } catch (ClassNotFoundException ignore) {
            return false;
        }
        return false;
    }
}

