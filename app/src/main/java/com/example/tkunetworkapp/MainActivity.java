package com.example.tkunetworkapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tkunetworkapp.beans.MyDataResult;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
	// UI Components
	private ProgressBar progressLoading;
	private Button jsonContentButton;
	private TextView jsonContentText;
	private Button parsedContentButton;
	private TextView parsedContentText;

	// The working queue of Volley
	private RequestQueue requestQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		//		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		//		fab.setOnClickListener(new View.OnClickListener() {
		//			@Override
		//			public void onClick(View view) {
		//				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
		//						.setAction("Action", null).show();
		//			}
		//		});

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		this.execute();
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//		int id = item.getItemId();
		//
		//		//noinspection SimplifiableIfStatement
		//		if (id == R.id.action_settings) {
		//			return true;
		//		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		//		int id = item.getItemId();

		//		if (id == R.id.nav_camara) {
		//			// Handle the camera action
		//		} else if (id == R.id.nav_gallery) {
		//
		//		} else if (id == R.id.nav_slideshow) {
		//
		//		} else if (id == R.id.nav_manage) {
		//
		//		} else if (id == R.id.nav_share) {
		//
		//		} else if (id == R.id.nav_send) {
		//
		//		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	/*
	 * Helper Methods
	 */
	private void execute() {
		this.initApiRequestQueue();
		this.prepareUI();
		this.prepareEvents();
		this.invokeStringRequest();
	}

	private void initApiRequestQueue() {
		this.requestQueue = Volley.newRequestQueue(this);
	}

	private void prepareUI() {
		this.progressLoading = (ProgressBar) findViewById(R.id.progress_loading);
		this.jsonContentButton = (Button) findViewById(R.id.btn_json_content);
		this.jsonContentText = (TextView) findViewById(R.id.text_json_content);
		this.parsedContentButton = (Button) findViewById(R.id.btn_parsed_content);
		this.parsedContentText = (TextView) findViewById(R.id.text_parsed_content);
	}

	private void prepareEvents() {
		this.jsonContentButton.setOnClickListener(this.buttonOnClickListener);
		this.parsedContentButton.setOnClickListener(this.buttonOnClickListener);
	}

	/*
	 * API Request Methods
	 */
	private void invokeStringRequest() {
		String requestURL = "http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=201d8ae8-dffc-4d17-ae1f-e58d8a95b162&limit=10";

		final StringRequest getDataRequest = new StringRequest(
				Request.Method.GET,
				requestURL,
				new Response.Listener<String>() {
					@Override
					public void onResponse(final String response) {
						Gson gson = new Gson();
						MyDataResult myDataResult = gson.fromJson(response, MyDataResult.class);

						String tmpResult = "";
						for (MyDataResult.ResultItem resultItem : myDataResult.getResult().getResults()) {
							tmpResult += resultItem.get_id() + "\n" + resultItem.getAPP_NAME() + "\n" + resultItem.getC_NAME() + "\n"+
									resultItem.getADDR() + "\n" + resultItem.getCO_TI() +"\n\n";
						}

						jsonContentText.setText(response);
						parsedContentText.setText(tmpResult);

						progressLoading.setVisibility(View.GONE);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						progressLoading.setVisibility(View.GONE);
					}
				});
		new Thread(new Runnable() {
			@Override
			public void run() {
				progressLoading.setVisibility(View.VISIBLE);
			}
		}).start();
		requestQueue.add(getDataRequest);
	}

	/*
	 * Events
	 */
	private View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			this.determineButton(v.getId());
		}

		private void determineButton(int viewId) {
			switch (viewId) {
				case R.id.btn_json_content:
					jsonContentText.setVisibility(View.VISIBLE);
					parsedContentText.setVisibility(View.GONE);
					break;

				case R.id.btn_parsed_content:
					jsonContentText.setVisibility(View.GONE);
					parsedContentText.setVisibility(View.VISIBLE);
					break;
			}
		}
	};
}
