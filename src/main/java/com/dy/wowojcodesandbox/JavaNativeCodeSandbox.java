package com.dy.wowojcodesandbox;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.dy.wowojcodesandbox.model.ExecuteCodeRequest;
import com.dy.wowojcodesandbox.model.ExecuteCodeResponse;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class JavaNativeCodeSandbox implements CodeSandbox{
    private static final String GLOBAL_CODE_DIR_NAME = "tmpCode";
    private static final String GLOBAL_JAVA_CLASS_NAME = "Main.java";

    public static void main(String[] args){
        JavaNativeCodeSandbox javaNativeCodeSandbox = new JavaNativeCodeSandbox();
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        executeCodeRequest.setInputList(Arrays.asList("1 2","3 4"));
        executeCodeRequest.setLanguage("java");
        String code = ResourceUtil.readStr("testCode/simpleComputeArg/Main.java",StandardCharsets.UTF_8);
        executeCodeRequest.setCode(code);
        ExecuteCodeResponse executeCodeResponse = javaNativeCodeSandbox.executeCode(executeCodeRequest);
        System.out.println(executeCodeResponse);
    }
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        String language = executeCodeRequest.getLanguage();
        String code = executeCodeRequest.getCode();
        //1.把用户代码保存为文件
        String userDir = System.getProperty("user.dir");
        String globalCodePathName = userDir + File.separator + GLOBAL_CODE_DIR_NAME;
        //判断代码目录是否存在,若不存在,则创建
        if(FileUtil.exist(globalCodePathName)){
            FileUtil.mkdir(globalCodePathName);
        }
        //将用户代码隔离存储
        String userCodeParentPath = globalCodePathName + File.separator + UUID.randomUUID();
        String userCodePath = userCodeParentPath + File.separator + GLOBAL_JAVA_CLASS_NAME;
        File userCodeFile = FileUtil.writeString(code,userCodePath, StandardCharsets.UTF_8);

        //2.编译代码,得到.class文件
        String compileCmd = String.format("javac -encoding utf-8 %s", userCodeFile.getAbsolutePath());
        return null;
    }
}
