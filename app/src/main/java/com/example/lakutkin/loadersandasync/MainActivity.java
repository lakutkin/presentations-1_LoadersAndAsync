package com.example.lakutkin.loadersandasync;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
	private static class MyLoader extends TrueAsyncLoader<String> {
		public MyLoader(Context context) {
			super(context);
		}

		@Override
		public String loadInBackground() {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Test";
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<String> onCreateLoader(int id, Bundle args) {
		return new MyLoader(this);
	}

	@Override
	public void onLoadFinished(Loader<String> loader, String data) {
		Log.d("Loaders", "onLoadFinished");
		TextView textView = (TextView) findViewById(R.id.textView);
		textView.setText(data);
	}

	@Override
	public void onLoaderReset(Loader<String> loader) {

	}
}
