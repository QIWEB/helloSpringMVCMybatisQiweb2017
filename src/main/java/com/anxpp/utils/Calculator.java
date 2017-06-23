package com.anxpp.utils;

import javax.script.ScriptEngine;

import javax.script.ScriptEngineManager;

import javax.script.ScriptException;

public final class Calculator {  
	/*java执行javascript脚本java6内置JS引擎初接触**/
    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    public static Object cal(String expression) throws ScriptException{

        return jse.eval(expression);

    }

}