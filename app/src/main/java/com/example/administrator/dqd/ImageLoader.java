package com.example.administrator.dqd;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ImageLoader {

	private ImageView mImageView;
	private String mUrl;
	private LruCache<String, Bitmap> mMemoryCaches;
	private Set<NewsAsyncTask> mTasks;
	private ListView mListView;

	public String mUrls[];

	public ImageLoader(ListView listView) {

		this.mListView = listView;

		mTasks = new HashSet<NewsAsyncTask>();

		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheSizes = maxMemory / 5;

		mMemoryCaches = new LruCache<String, Bitmap>(cacheSizes) {
			@SuppressLint("NewApi")
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getByteCount();
			}
		};

	}

	public Bitmap getBitmapFromLrucache(String url) {

		return mMemoryCaches.get(url);
	}

	public void addBitmapToLrucaches(String url, Bitmap bitmap) {

		if (getBitmapFromLrucache(url) == null) {
			mMemoryCaches.put(url, bitmap);
		}

	}

//	public void loadImages(int start, int end) {
//
//		for (int i = start; i < end; i++) {
//			String loadUrl = mUrls[i];
//			if (getBitmapFromLrucache(loadUrl) != null) {
//				ImageView imageView = (ImageView) mListView
//						.findViewWithTag(loadUrl);
//
//				imageView.setImageBitmap(getBitmapFromLrucache(loadUrl));
//			} else {
//				NewsAsyncTask mNewsAsyncTask = new NewsAsyncTask(loadUrl);
//				mTasks.add(mNewsAsyncTask);
//				mNewsAsyncTask.execute(loadUrl);
//			}
//		}
//	}

	public void showImage(ImageView imageView, String url) {

		Bitmap bitmap = getBitmapFromLrucache(url);
		if (bitmap == null) {
			imageView.setImageResource(R.drawable.loading);
		} else {
			imageView.setImageBitmap(bitmap);
		}
	}

	public void cancelAllAsyncTask() {
		if (mTasks != null) {
			for (NewsAsyncTask newsAsyncTask : mTasks) {
				newsAsyncTask.cancel(false);
			}
		}

	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (mImageView.getTag().equals(mUrl)) {
				mImageView.setImageBitmap((Bitmap) msg.obj);
			}
		};
	};

	// 1.多线程的方法
	public void showImageByThead(ImageView iv, final String url) {
		mImageView = iv;
		mUrl = url;
		new Thread() {
			public void run() {
				Bitmap bitmap = getBitmapFromUrl(url);
				Message message = Message.obtain();
				message.obj = bitmap;
				mHandler.sendMessage(message);
			};
		}.start();
	}

	public Bitmap getBitmapFromUrl(String urlString) {
		Bitmap bitmap;
		InputStream is = null;
		try {
			URL mUrl = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) mUrl
					.openConnection();
			is = new BufferedInputStream(connection.getInputStream());
			bitmap = BitmapFactory.decodeStream(is);
			connection.disconnect();
			return bitmap;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void showImageByAsyncTask(ImageView imageView, String url) {
		// ����ȥLruCache��ȥ��ͼƬ
		Bitmap bitmap = getBitmapFromLrucache(url);
		// �����Ϊ�գ�˵��LruCache���Ѿ������˸�ͼƬ�����ȡ����ֱ����ʾ��
		if (bitmap != null) {
//			ImageView imageView2 = (ImageView) mListView.findViewWithTag(url);
//			imageView2.setImageBitmap(bitmap);
			imageView.setImageBitmap(bitmap);
		} else {
			// ���������û�еĻ��Ϳ����첽����ȥ����ͼƬ��
			new NewsAsyncTask(imageView,url).execute(url);
		}

	}

	class NewsAsyncTask extends AsyncTask<String, Void, Bitmap> {

		private String mUrl;
		private ImageView mimageView;

		public NewsAsyncTask(ImageView imageView,String url) {
			mUrl = url;
			mImageView = imageView;
		}

		@Override
		protected Bitmap doInBackground(String... params) {

			String url = params[0];
			Bitmap bitmap;

			bitmap = getBitmapFromUrl(url);
			// �������֮������뵽LruCache�������´μ��ص�ʱ�򣬾Ϳ���ֱ�Ӵ�LruCache��ֱ�Ӷ�ȡ
			if (bitmap != null) {
				addBitmapToLrucaches(url, bitmap);
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);



			if(mImageView.getTag().equals(mUrl)){
				mImageView.setImageBitmap(bitmap);
			}


		}

	}

}
