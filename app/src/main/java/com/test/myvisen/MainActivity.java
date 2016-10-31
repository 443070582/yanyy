package com.test.myvisen;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.test.myvisen.gongju.Ad;
import com.test.myvisen.lina.json.BataBean;
import com.test.myvisen.lina.json.DataBean;
import com.test.myvisen.lina.json.MfBean;
import com.test.myvisen.lina.json.ZxBean;
import com.test.myvisen.uil.data.Data;
import com.test.myvisen.zidingyi.HTTPUtils;
import com.test.myvisen.zidingyi.HorizontalListView;
import com.test.myvisen.zidingyi.MyGridView;
import com.test.myvisen.zidingyi.VolleyListener;

import net.tsz.afinal.FinalBitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends Activity implements OnClickListener
{
	private ViewPager viewPager;
	private List<Ad> adlist = new ArrayList<Ad>();
	private List<DataBean> databean = new ArrayList<DataBean>();
	private List<BataBean> batabean = new ArrayList<BataBean>();
	private List<MfBean> mfbean = new ArrayList<MfBean>();
	private List<ZxBean> zxbean = new ArrayList<ZxBean>();

	private TextView tvText;
	private LinearLayout linearDot;
	private MyBase myBase;
	private boolean isBack;
	protected void onCreate(Bundle savedInstanceState)
	{
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fb = FinalBitmap.create(MainActivity.this);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		linearDot = (LinearLayout) findViewById(R.id.dot);
		holistview = (HorizontalListView) findViewById(R.id.hoListV);
		jx_listview = (ListView) findViewById(R.id.jx_listview);
		mfgridview = (MyGridView) findViewById(R.id.gridView1);
		zhibogrid = (MyGridView) findViewById(R.id.gridView2);
		initListener();
		initData();
		
		
	}

	//轮播图
	private void initListener()
	{
		           for (int i=0;i<Data.ImageID.length;i++)
			     {
				     adlist.add(new Ad(Data.ImageID[i]));
			     }
		            initDots();
		            viewPager.setAdapter(new MyViewPager());
				int centerValue = Integer.MAX_VALUE / 2;
				int value = centerValue % adlist.size();
				viewPager.setCurrentItem(centerValue - value);
				updateTextAndDot();
				h.sendEmptyMessageDelayed(0, 3000);
		       viewPager.setOnPageChangeListener(new OnPageChangeListener()
			 {
			@Override
			public void onPageSelected(int position)
			{
				updateTextAndDot();

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels)
			{

			}

			@Override
			public void onPageScrollStateChanged(int state)
			{
				// TODO Auto-generated method stub
			}
		});
	}

	private Handler h = new Handler()
	{
		public void handleMessage(Message msg)
		{
			viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
			h.sendEmptyMessageDelayed(0, 3000);
		};
	};
	private FinalBitmap fb;
	private HorizontalListView holistview;
	private ListView jx_listview;
	private MyGridView mfgridview;
	private MyGridView zhibogrid;
	private void updateTextAndDot()
	{
		int currentPage = viewPager.getCurrentItem() % adlist.size();
		for (int i = 0; i < linearDot.getChildCount(); i++)
		{
			linearDot.getChildAt(i).setEnabled(i == currentPage);
		}
	}

	//导航信息栏
	private void initDots()
	{
		for (int i = 0; i < adlist.size(); i++)
		{
			View view=new View(this);
			LayoutParams params=new LayoutParams(8,8);
			if(i!=0){
				params.leftMargin=5;
			}
			
			view.setLayoutParams(params);
			view.setBackgroundResource(R.drawable.selecter_dot);
			linearDot.addView(view);
		}
	}
	//导航信息数据栏
	private void initData() {
		HTTPUtils.get(MainActivity.this, Data.HENG_LIST, new VolleyListener() {
			@Override
			public void onResponse(String arg0) {
				try {
			
					JSONObject jsonObject = new JSONObject(arg0.toString());
					JSONArray jsonArray = jsonObject.getJSONArray("data");
					for (int i = 0; i < jsonArray.length(); i++) {
						String clasid = jsonArray.getJSONObject(i).getString("classid");
						String clasname = jsonArray.getJSONObject(i).getString("classname");
						
						String clasimg = jsonArray.getJSONObject(i).getString("classimg");
						DataBean bean = new DataBean(clasid,clasimg,clasname);
						databean.add(bean);
						MfData();
						if(myBase!=null){
							myBase.notifyDataSetChanged();
						}else{
							myBase = new MyBase();
							holistview.setAdapter(myBase);
						}
						
					}
				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	//精选推荐
	private void initBata() {
	HTTPUtils.get(MainActivity.this, Data.ADMIN_LOGIN, new VolleyListener() {
		private MyBadalist badalist;

		@Override
		public void onResponse(String arg0) {
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(arg0.toString());
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					String price = jsonArray.getJSONObject(i).getString("price_member");
					String name = jsonArray.getJSONObject(i).getString("name");
					String imaurl = jsonArray.getJSONObject(i).getString("photourl");
					String lookcount = jsonArray.getJSONObject(i).getString("lookcount");
					BataBean btbean = new BataBean(imaurl, name, price,lookcount);
					  batabean.add(btbean);
					 
					  if(badalist!=null){
						  badalist.notifyDataSetChanged();
					  }else{
						   badalist = new MyBadalist();
						   jx_listview.setAdapter(badalist);
						   
					  }
					 
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		@Override
		public void onErrorResponse(VolleyError arg0) {
			// TODO Auto-generated method stub
			
		}
	});
		
	}
	//免费体验
	private void MfData(){
		HTTPUtils.get(this, Data.MIANFEI_list, new VolleyListener() {
			
			private MyGridview gridbase;

			@Override
			public void onResponse(String arg0) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(arg0.toString());
					
					JSONArray jsonArray = jsonObject.getJSONArray("data");
					for (int i = 0; i < jsonArray.length(); i++)
					{
						String name = jsonArray.getJSONObject(i).getString("name");
						String imaurl = jsonArray.getJSONObject(i).getString("photourl");
						
						MfBean mfbeang = new MfBean(name, imaurl);
						mfbean.add(mfbeang);
//						initBata();
						ZxData();
						if(gridbase!=null){
							gridbase.notifyDataSetInvalidated();
						}else{
							gridbase = new MyGridview();
							mfgridview.setAdapter(gridbase);
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
	//在线直播
	private void ZxData(){
		HTTPUtils.get(this, Data.ZHIBO_LIST, new VolleyListener() {
			
			private ZhiboBase gridbase;

			@Override
			public void onResponse(String arg0) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(arg0.toString());				
					JSONArray jsonArray = jsonObject.getJSONArray("data");
					for (int i = 0; i < jsonArray.length(); i++)
					{
						String name = jsonArray.getJSONObject(i).getString("name");
						String imaurl = jsonArray.getJSONObject(i).getString("photourl");
						String courseid = jsonArray.getJSONObject(i).getString("courseid");
						ZxBean zxbeang = new ZxBean(name, imaurl,courseid);
						zxbean.add(zxbeang);
						initBata();
						if(gridbase!=null){
							gridbase.notifyDataSetInvalidated();
						}else{
							gridbase = new ZhiboBase();
							zhibogrid.setAdapter(gridbase);
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
	class MyViewPager extends PagerAdapter
	{
		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}


		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}


		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Ad adImage = adlist.get(position % adlist.size());
			View view = View.inflate(MainActivity.this, R.layout.adapter, null);
			ImageView image = (ImageView) view.findViewById(R.id.image);
			image.setImageResource(adImage.getImageId());
			container.addView(view);
			return view;
		}


		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// super.destroyItem(container, position, object);
			container.removeView((View) object);
		}
	}

	
	class MyBase extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return databean.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			ViewHolder holder;
			
			if(convertView==null)
			{
				convertView = getLayoutInflater().inflate(R.layout.tem, null);
				holder = new ViewHolder();
				holder.name = (TextView) convertView.findViewById(R.id.name);
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				
				convertView.setTag(holder);
			}
			     holder = (ViewHolder) convertView.getTag();
			
			holder.name.setText(databean.get(position).getClassimg());
			fb.display(holder.img, databean.get(position).getClassname());
			return convertView;
		}
		
	}

	//精选适配
class MyBadalist extends BaseAdapter
	{

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return batabean.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View inflate = getLayoutInflater().inflate(R.layout.tim, null);
		TextView name = (TextView) inflate.findViewById(R.id.name);
		TextView price = (TextView) inflate.findViewById(R.id.price);
		ImageView imguri = (ImageView) inflate.findViewById(R.id.imgurl);
		TextView renshu = (TextView) inflate.findViewById(R.id.renshu);
		name.setText(batabean.get(position).getName());
		price.setText(batabean.get(position).getPrice_member());
		renshu.setText(batabean.get(position).getLookcount()+"人在学");
		fb.display(imguri, batabean.get(position).getPhotourl());
		return inflate;
	}
	
}


class MyGridview extends BaseAdapter
{

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mfbean.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View inflate = getLayoutInflater().inflate(R.layout.grid_tim, null);
		ImageView gridimg = (ImageView) inflate.findViewById(R.id.grid_img);
		TextView gridname = (TextView) inflate.findViewById(R.id.grid_name);
		gridname.setText(mfbean.get(position).getName());
		fb.display(gridimg, mfbean.get(position).getPhotourl());
		return inflate;
	}
	
}
//在线直播适配器
class ZhiboBase extends BaseAdapter
{

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return zxbean.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View inflate = getLayoutInflater().inflate(R.layout.grid_tim, null);
		ImageView gridimg = (ImageView) inflate.findViewById(R.id.grid_img);
		TextView gridname = (TextView) inflate.findViewById(R.id.grid_name);
		TextView gridcourse = (TextView) inflate.findViewById(R.id.grid_course);
		gridname.setText(zxbean.get(position).getName());
		gridcourse.setVisibility(View.VISIBLE);
		fb.display(gridimg, zxbean.get(position).getPhotourl());
		gridcourse.setText(zxbean.get(position).getCourseid());
		
		return inflate;
	}
	
}
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		
	}
	class ViewHolder 
	{

	  private	TextView name;
	  private	ImageView img;

	} 
	
	
}
