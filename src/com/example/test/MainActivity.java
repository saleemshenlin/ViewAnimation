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
				// ���ֻ��setFillAfter���������ƶ����λ�ã���ʵλ�ò����ƶ�
				selection.startAnimation(showAnim);
				// ��������View��һ��listview,������Ҫ��ִ��selection.requestFocusFromTouch();����ڶ��β�����ʾ����
				// ��Ҫ������Ŀؼ� enable��Ϊfalse,��ֹ��������Ŀؼ�
				show.setEnabled(false);
				// ������Ϊfalse,��Ϊ�������������Σ��ͻ�����ִ�������ƶ�λ�ã�������������Ҫ�Ľ��
				// �Ȱ����µ�view�ƻ�ȥ������Ϊtrue
				showAnim.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						hidden.setEnabled(true);
						// ���������������󣬰����صİ�ť��Ϊ����
						selection.clearAnimation();
						selection.layout(selection.getLeft(), 0,
								selection.getRight(), 200);
						// selection.setLayoutParams(new
						// AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.FILL_PARENT,
						// 200, 0, 0){});
						// �������й�����ͬ
						// �ƶ��ؼ�������������λ�ã�clearAnimation�������������������Ļ�Ͳ�������û�еĻ�����
						// setFillAfter����Ϊtrue,��Ȼ��ʹΪtrue���ؼ���ʵλ��Ҳ����䣬�������ǿ�����λ���ǻ��ģ��������layout���������ǿ�����λ�û����ٱ�
						System.out.println(selection.getLeft() + " "
								+ selection.getTop() + " "
								+ selection.getRight() + " "
								+ selection.getBottom());
						// ����ƶ����λ�ã��������ԣ������ʹ��layout�����ƶ��ؼ�������ǰ�������󣬿ؼ���λ�ö��ǲ����
						// ���ԣ�����������������
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