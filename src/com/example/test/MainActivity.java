package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	LinearLayout main, selection;
	Button hidden, show;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		main = (LinearLayout) findViewById(R.id.main);
		selection = (LinearLayout) findViewById(R.id.selection);
		hidden = (Button) findViewById(R.id.hidden);
		show = (Button) findViewById(R.id.show);
		show.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Animation showAnim = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.show);
				// translate.setFillAfter(true);
				// 如果只用setFillAfter方法保存移动后的位置，真实位置不会移动
				selection.startAnimation(showAnim);
				// 如果下面的View是一个listview,可能需要先执行selection.requestFocusFromTouch();否则第二次不会显示动画
				// 需要把下面的控件 enable设为false,防止点中下面的控件
				show.setEnabled(false);
				// 必须设为false,因为如果连续点击两次，就会连着执行两次移动位置，并不是我们想要的结果
				// 等把拉下的view移回去后，再设为true
				showAnim.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						hidden.setEnabled(true);
						// 当下拉动画结束后，把隐藏的按钮设为可用
						selection.clearAnimation();
						selection.layout(selection.getLeft(), 0,
								selection.getRight(), 200);
						// selection.setLayoutParams(new
						// AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.FILL_PARENT,
						// 200, 0, 0){});
						// 上面两行功能相同
						// 移动控件到动画结束的位置，clearAnimation方法可以清除动画，屏幕就不会闪，没有的话会闪
						// setFillAfter不能为true,虽然即使为true，控件真实位置也不会变，但是我们看到的位置是会变的，如果再用layout方法，我们看到的位置还会再变
						System.out.println(selection.getLeft() + " "
								+ selection.getTop() + " "
								+ selection.getRight() + " "
								+ selection.getBottom());
						// 输出移动后的位置，经过测试，如果不使用layout方法移动控件，动画前，动画后，控件的位置都是不变的
						// 所以，亲眼所见并非真相
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
						System.out.println(selection.getLeft() + " "
								+ selection.getTop() + " "
								+ selection.getRight() + " "
								+ selection.getBottom());
					}
				});
			}
		});
		hidden.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Animation hiddenAnim = AnimationUtils.loadAnimation(
						MainActivity.this, R.anim.hidden);
				selection.startAnimation(hiddenAnim);
				hidden.setEnabled(false);
				hiddenAnim.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						show.setEnabled(true);
						selection.clearAnimation();
						selection.layout(selection.getLeft(), -190,
								selection.getRight(), 10);
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}
				});

			}
		});

	}
}