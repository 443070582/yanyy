package com.test.myvisen.zidingyi;

/**
 * Copyright 2013 Ognyan Bankov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Helper class that is used to provide references to initialized
 * RequestQueue(s) and ImageLoader(s)
 * 
 * @author Ognyan Bankov
 * 
 */
public class HTTPUtils
{
	private static RequestQueue mRequestQueue;
	private static String tag = "MyTag";

	private HTTPUtils() {
	}

	private static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);

	}

	public static void post(Context context, String url, final Map<String, String> params,
			final VolleyListener listener) {
//		if (mRequestQueue != null) {
//			mRequestQueue.cancelAll(tag);
//		}
		
		UTFStringRequest myReq = new UTFStringRequest(Method.POST, url, new Listener<String>() {
			public void onResponse(String response) {
				listener.onResponse(response);
			}
		}, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				listener.onErrorResponse(error);
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				return params;
			}
		};
		if (mRequestQueue == null) {
			init(context);
		}
		myReq.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));
		myReq.setTag(tag);
		// 请用缓存
		myReq.setShouldCache(true);
		// 设置缓存时间10分钟 
		// myReq.setCacheTime(10*60);
		mRequestQueue.add(myReq);
	}

	public static void get(Context context, String url, final VolleyListener listener) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
		StringRequest myReq = new UTFStringRequest(Method.GET, url, new Listener<String>() {
			public void onResponse(String response) {
				listener.onResponse(response);
			}
		}, new Response.ErrorListener() {
			public void onErrorResponse(VolleyError error) {
				listener.onErrorResponse(error);
			}
		});
		if (mRequestQueue == null) {
			init(context);
		}
		myReq.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 1, 1.0f));
		myReq.setTag(tag);
		mRequestQueue.add(myReq);
	}

	private static RequestQueue getRequestQueue(Context context) {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} else {
			throw new IllegalStateException("RequestQueue not initialized");
		}
	}

	public static void cancelAll(Context context) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

	/**
	 * Returns instance of ImageLoader initialized with {@see FakeImageCache}
	 * which effectively means that no memory caching is used. This is useful
	 * for images that you know that will be show only once.
	 * 
	 * @return
	 */
	// public static ImageLoader getImageLoader() {
	// if (mImageLoader != null) {
	// return mImageLoader;
	// } else {
	// throw new IllegalStateException("ImageLoader not initialized");
	// }
	// }
}
