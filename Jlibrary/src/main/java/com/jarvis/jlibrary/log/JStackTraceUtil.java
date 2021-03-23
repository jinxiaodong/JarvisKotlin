package com.jarvis.jlibrary.log;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/22/21
 */
class JStackTraceUtil {




    public static StackTraceElement[] getCroppedRealStackTrace(StackTraceElement[] stackTrace,String ignorePackageName,int maxDepth){
        return  cropStackTrace(getRealStackTrace(stackTrace,ignorePackageName),maxDepth);
    }

    /**
     * 忽略系统包名下的堆栈信息
     * @param stackTrace
     * @param ignorePackage
     * @return
     */
    private static StackTraceElement[] getRealStackTrace(StackTraceElement[] stackTrace, String ignorePackage) {
        int ignoreDepth = 0;
        int allDepth = stackTrace.length;
        String className;
        for (int i = allDepth - 1; i >= 0; i--) {
            className = stackTrace[i].getClassName();
            if (ignorePackage != null && className.startsWith(ignorePackage)) {
                ignoreDepth = i + 1;
                break;
            }
        }

        int realDepth = allDepth - ignoreDepth;
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(stackTrace,ignoreDepth,realStack,0,realDepth);

        return  realStack;
    }

    /**
     * 堆栈信息裁剪
     *
     * @param callStack
     * @param maxDepth
     * @return
     */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] callStack, int maxDepth) {
        int realDepth = callStack.length;
        if (maxDepth > 0) {
            realDepth = Math.min(realDepth, maxDepth);

        }
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(callStack, 0, realStack, 0, realDepth);
        return realStack;
    }
}
