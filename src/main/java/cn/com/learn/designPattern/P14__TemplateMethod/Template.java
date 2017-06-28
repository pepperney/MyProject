package cn.com.learn.designPattern.P14__TemplateMethod;

/**
 * 定义一个是抽象类作为模板方法类，子类继承这个类 ，overide需要使用的方法（如果定义成接口，则实现类需要实现所有方法） 
 * 
 * 例如这里templateMethod方法按顺序执行三个方法，实现类也会按顺序执行这个方法
 *
 * @author pepper
 *
 */
public abstract class Template {
	
	public abstract void start(); 

	public abstract void process(); 

	public abstract void end(); 

	public void templateMethod(){
		this.start();
		this.process();
		this.end();
	}
}
