package cn.com.learn.designPattern.P11__Composite;

import java.util.List;


/**
 * 注意这里的Leaf是树形结构中的最基本的元素，所以不可以有remove，add，getAll行为，
 * 但是它又实现了Component接口，所以这几个方法必须保持为空实现，避免发生不合理的行为
 * 
 * @author pepper
 *
 */
public class Leaf implements Component {

	@Override
	public void add(Component component) {
	}

	@Override
	public List<Component> getAll() {
		return null;
	}

	@Override
	public void remove(Component component) {
	}

	@Override
	public void method() {

		System.out.println("我是树叶");

	}

}
