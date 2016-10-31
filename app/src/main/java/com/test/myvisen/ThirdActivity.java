package com.test.myvisen;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.test.myvisen.lina.json.ChBean;
import com.test.myvisen.lina.json.ThirdBean;
import com.test.myvisen.uil.data.Data;
import com.test.myvisen.zidingyi.HTTPUtils;
import com.test.myvisen.zidingyi.VolleyListener;
import com.test.myvisen.zidingyi.XListView;
import com.test.myvisen.zidingyi.XListView.IXListViewListener;

import net.tsz.afinal.FinalBitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThirdActivity extends Activity implements OnClickListener, IXListViewListener {
	
	private List<ThirdBean> batabean = new ArrayList<ThirdBean>();
	private FinalBitmap fb;
	private XListView ws_lv;	
	private List<ChBean> ch_bean = new ArrayList<ChBean>();
	private MyThirdList thirdList;
	private TextView wangluo;
	private TextView mianshou;
	private TextView zhibo;
	private DrawerLayout drawlayou;
	private MyCHbase myCHbase;
	private ListView ch_listview;
	private  String js="jsonid=5384";
	private String chjs="jsonid=5389";
	private int page = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_third);
		fb = FinalBitmap.create(ThirdActivity.this);
		ch_listview = (ListView) findViewById(R.id.cehualv);
		ws_lv = (XListView) findViewById(R.id.th_listview);
		ws_lv.setPullLoadEnable(true);
		ws_lv.setPullRefreshEnable(true);
		ws_lv.setXListViewListener(this);//设置监听事件，重写两个方法
		wangluo = (TextView) findViewById(R.id.wangluo);
		wangluo.setOnClickListener(this);
		setMyColor();
		mianshou = (TextView) findViewById(R.id.mianshou);
		mianshou.setOnClickListener(this);
		zhibo = (TextView) findViewById(R.id.zhibo);
		zhibo.setOnClickListener(this);
		drawlayou = (DrawerLayout) findViewById(R.id.drawerlayou);
		findViewById(R.id.leibie).setOnClickListener(this);
		thirdData();
		chData();

	}
	//网授数据
	public void thirdData() {
		if(page==2){
			js=js+"&page=2";
		}
		HTTPUtils.get(ThirdActivity.this, Data.HOST+js, new VolleyListener() {
			@Override
			public void onResponse(String arg0) {
				
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(arg0.toString());
					JSONArray jsonArray = jsonObject.getJSONArray("data");
					
					for (int i = 0; i < jsonArray.length(); i++) {
						String photourl = jsonArray.getJSONObject(i).getString("photourl");
						String name = jsonArray.getJSONObject(i).getString("name");
						String price_member = jsonArray.getJSONObject(i).getString("price_member");
						String lookcount = jsonArray.getJSONObject(i).getString("lookcount");
						ThirdBean msthirdBean = new ThirdBean(name, photourl, lookcount, price_member);
						batabean.add(msthirdBean);
						if(thirdList!=null){
							thirdList.notifyDataSetChanged();
						}else{
							thirdList = new MyThirdList();
							ws_lv.setAdapter(thirdList);
							thirdList.notifyDataSetChanged();
						}	
					}
					page++;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				onLoad();
			}
			
			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
	//侧滑数据
	public void chData(){
		
		HTTPUtils.get(this, Data.HOST+chjs, new VolleyListener() {
			@Override
			public void onResponse(String arg0) {
				JSONObject jsonObject;
		
				try {
					jsonObject = new JSONObject(arg0.toString());
				
					JSONArray jsonArray = jsonObject.getJSONArray("data");
					for (int i = 0; i < jsonArray.length(); i++) {
						String classname = jsonArray.getJSONObject(i).getString("classname");
						String classimg = jsonArray.getJSONObject(i).getString("classimg");
						String classid = jsonArray.getJSONObject(i).getString("classid");
						ChBean Bean = new ChBean(classid, classname, classimg);
						ch_bean.add(Bean);
						thirdData();
						if(myCHbase!=null){
							myCHbase.notifyDataSetChanged();
						}else{
							myCHbase = new MyCHbase();
						ch_listview.setAdapter(myCHbase);
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void onErrorResponse(VolleyError arg0) {
				Log.e("失败", arg0.toString());
				
			}
		});
	}
		

	//网授适配器
class MyThirdList extends BaseAdapter
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
		renshu.setVisibility(View.VISIBLE);
		name.setText(batabean.get(position).getName());
		price.setText(batabean.get(position).getPrice_member());
		renshu.setText(batabean.get(position).getLookcount()+"人在学");
	
		fb.display(imguri, batabean.get(position).getPhotourl());
		return inflate;
	}
	
}

public  void setMyColor()
{
	wangluo.setBackgroundResource(R.drawable.tabbg2_on);
	wangluo.setTextColor(Color.parseColor("#FFFFFF"));

}
@Override
public void onClick(View v)
{
 switch (v.getId())
 {
    case R.id.mianshou:
	chjs="jsonid=5389";	
		js="jsonid=5386";
	ChResh();
	page = 1;

//	onRefresh();
	mianshou.setBackgroundResource(R.drawable.tabbg2_on);
	zhibo.setBackgroundColor(Color.WHITE);
	zhibo.setBackgroundResource(R.drawable.tabbg3);
	zhibo.setTextColor(Color.parseColor("#129CDB"));
	wangluo.setTextColor(Color.parseColor("#129CDB"));
	wangluo.setBackgroundResource(R.drawable.tabbg1);
	mianshou.setTextColor(Color.parseColor("#FFFFFF"));
	
	break;
case R.id.zhibo:
	chjs="jsonid=5380";
	js="jsonid=5385";
	ChResh();
	page = 1;
	
//	onRefresh();
	zhibo.setBackgroundResource(R.drawable.tabbg3_on);
	mianshou.setBackgroundColor(Color.WHITE);
	mianshou.setTextColor(Color.parseColor("#129CDB"));
	wangluo.setTextColor(Color.parseColor("#129CDB"));
	mianshou.setBackgroundResource(com.test.myvisen.R.drawable.tabbg2);
	wangluo.setBackgroundResource(com.test.myvisen.R.drawable.tabbg1);
	zhibo.setTextColor(Color.parseColor("#FFFFFF"));
	break;
case R.id.wangluo:
	js="jsonid=5384";
	chjs="jsonid=5380";
	ChResh();
	page = 1;

	wangluo.setBackgroundResource(com.test.myvisen.R.drawable.tabbg1_on);
	zhibo.setBackgroundColor(Color.WHITE);
	zhibo.setBackgroundResource(com.test.myvisen.R.drawable.tabbg3);
	zhibo.setTextColor(Color.parseColor("#129CDB"));
	mianshou.setTextColor(Color.parseColor("#129CDB"));
	mianshou.setBackgroundColor(Color.WHITE);
	mianshou.setBackgroundResource(com.test.myvisen.R.drawable.tabbg2);
	wangluo.setTextColor(Color.parseColor("#FFFFFF"));
	break;
case R.id.leibie:
	drawlayou.openDrawer(Gravity.LEFT);
	break;
default:
	break;
}	
}


//侧滑菜单适配器
class MyCHbase extends BaseAdapter
{

	@Override
	public int getCount()
	{
		return ch_bean.size();
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View inflate = getLayoutInflater().inflate(R.layout.ch_list, null);
		ImageView img = (ImageView) inflate.findViewById(R.id.ch_img);
		TextView name = (TextView) inflate.findViewById(R.id.ch_name);
		fb.display(img, ch_bean.get(position).getClassimg());
		name.setText(ch_bean.get(position).getClassname());
		return inflate;
	}
	
}
public void ChResh()
{
	ch_bean.clear();
	ws_lv.setPullLoadEnable(false); 
	page = 1;
	batabean.clear();
	if (thirdList == null)
	{
		ws_lv.setAdapter(thirdList);
	} else
	{
		thirdList.notifyDataSetChanged();
	}
	chData();
}


@Override
public void onRefresh()
{
	onLoad();


}

@Override
public void onLoadMore()
{
	ws_lv.setPullRefreshEnable(false);
	thirdData();
	onLoad();

}

private void onLoad()
{
	new Handler().postDelayed(
		new Runnable() {
		@Override
		public void run()
		{
			ws_lv.stopRefresh();
			ws_lv.stopLoadMore();
			ws_lv.setRefreshTime(new Date().toLocaleString());
			ws_lv.setPullLoadEnable(true);
			ws_lv.setPullRefreshEnable(true);


		}
	}, 2000);
}
}
