package cn.com.learn.designPattern.P07__Decorator;

public class PictureDecorator implements IPicture{
	
	private IPicture picture;

	public PictureDecorator(IPicture picture){
		this.picture = picture;
	}
	
	@Override
	public void showPicture() {
		System.out.println("the author of this picture is Van Gogh,");
		this.picture.showPicture();
		System.out.println("It's name is sunflower");
	}

}
