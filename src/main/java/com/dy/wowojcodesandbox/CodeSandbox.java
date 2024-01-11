package com.dy.wowojcodesandbox;

import com.dy.wowojcodesandbox.model.ExecuteCodeRequest;
import com.dy.wowojcodesandbox.model.ExecuteCodeResponse;


/**
 * 代码沙箱
 */
public interface CodeSandbox {
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
