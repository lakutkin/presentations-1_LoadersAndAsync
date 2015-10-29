package com.example.lakutkin.loadersandasync;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
	private static class MyLoader extends TrueAsyncLoader<String> {
		public MyLoader(Context context) {
			super(context);
		}

		@Override
		public String loadInBackground() {
			try {
				Thread.sleep(5000);
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
		
		getSupportFragmentManager().beginTransaction().add(R.id.layout, new MyFragment(), "Tag").commit();
	}

	@Override
	public void onLoaderReset(Loader<String> loader) {

	}

	public static class MyFragment extends Fragment{
		@Nullable
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			return inflater.inflate(R.layout.f_test, container, false);
		}
	}
}
