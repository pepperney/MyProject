package cn.com.learn.designPattern.P11__Composite;

import java.util.List;

public interface Component {

	public void add(Component component);

	public void remove(Component component);

	public List<Component> getAll();

	public void method();
}
