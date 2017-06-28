package cn.com.learn.designPattern.P11__Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里的branch是由branch元素和leaf组合而成的
 * @author pepper
 *
 */
public class Branch implements Component {

	private List<Component> list = new ArrayList<Component>();

	public void add(Component component) {

		list.add(component);
	}

	public void remove(Component component) {

		list.remove(component);
	}

	public List<Component> getAll() {

		return this.list;
	}

	@Override
	public void method() {

		System.out.println("我是树枝");
		for (Component component : list) {
			component.method();
		}
	}
}
