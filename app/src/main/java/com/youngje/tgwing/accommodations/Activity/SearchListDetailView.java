package com.youngje.tgwing.accommodations.Activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.youngje.tgwing.accommodations.R;

import java.util.ArrayList;
import java.util.List;

import static com.youngje.tgwing.accommodations.R.drawable.face1;

/**
 * Created by JiwoonWon on 2016. 10. 22..
 */

public class SearchListDetailView extends AppCompatActivity{

    private TextView btnExit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_detail);

        reviewListView();
        btnExit = (TextView) findViewById(R.id.list_detail_view_exit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void reviewListView() {
        ListView listview;
        SearchListReviewViewAdapter adapter;

        adapter = new SearchListReviewViewAdapter();
        listview = (ListView) findViewById(R.id.list_detail_view_listview);
        listview.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.face1), 4,ContextCompat.getDrawable(this,R.drawable.googlelogo)
                ,"Nick","2016.10.23",ContextCompat.getDrawable(this,R.drawable.test_img_palace),"경복궁 정말 좋은 것 같아효",2000);
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.face1), 5,ContextCompat.getDrawable(this,R.drawable.googlelogo)
                ,"Jason","2016.10.21",ContextCompat.getDrawable(this,R.drawable.test_img_palace),"여친이 생기면 꼭 다시 와야겠어요!",1949);
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.face1), 3,ContextCompat.getDrawable(this,R.drawable.googlelogo)
                ,"Mark","2016.10.20",ContextCompat.getDrawable(this,R.drawable.test_img_palace),"전 별로였는데..?",392);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });
    }


/////////////////////////  Adapter
    public class SearchListReviewViewAdapter extends BaseAdapter {

        private List<SearchListReviewViewItem> reviewViewItemList = new ArrayList<SearchListReviewViewItem>();

        public SearchListReviewViewAdapter() {

        }

        @Override
        public int getCount() {
            return reviewViewItemList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            final int pos = position;
            final Context context = parent.getContext();

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_detail_item, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.ratingBar.setOnRatingBarChangeListener(onRatingChangedListener(viewHolder, position));
            viewHolder.ratingBar.setTag(position);
            viewHolder.ratingBar.setRating(getItem(position).getRatingStar());

            SearchListReviewViewItem listViewItem = reviewViewItemList.get(position);

            viewHolder.profileView.setImageDrawable(getItem(position).getProfileDrawable());
            viewHolder.ratingScore.setText(new Float(getItem(position).getRatingScore()).toString());
            viewHolder.userName.setText(getItem(position).getUserName());
            viewHolder.nationalityView.setImageDrawable(getItem(position).getNationality());
            viewHolder.reviewPictureView.setImageDrawable(getItem(position).getReviewDrawable());
            viewHolder.dateView.setText(getItem(position).getDate());
            viewHolder.reviewTextView.setText(getItem(position).getUserReview());
            viewHolder.likeNumView.setText(getItem(position).getLikeNum());

            return convertView;
        }

        private RatingBar.OnRatingBarChangeListener onRatingChangedListener(final SearchListReviewViewAdapter.ViewHolder holder,
                                                                            final int position) {
            return new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    SearchListReviewViewItem item = getItem(position);
                    item.setRatingStar(v);
                    Log.i("Adapter", "star: " + v);
                }
            };
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public SearchListReviewViewItem getItem(int position) {
            return reviewViewItemList.get(position);
        }

        public void addItem(Drawable profileDrawable, float ratingScore, Drawable nationality, String userName, String date
                            ,Drawable reviewDrawable, String userReview, int likeNum) {
            SearchListReviewViewItem item = new SearchListReviewViewItem();

            item.setProfileDrawable(profileDrawable);
            item.setRatingScore(ratingScore);
            item.setRatingStar(ratingScore);
            item.setNationality(nationality);
            item.setUserName(userName);
            item.setDate(date);
            item.setReviewDrawable(reviewDrawable);
            item.setUserReview(userReview);
            item.setLikeNum(likeNum);
            reviewViewItemList.add(item);
        }

        private class ViewHolder {
            private ImageView profileView;
            private RatingBar ratingBar;
            private TextView ratingScore;
            private TextView userName;
            private ImageView nationalityView;
            private ImageView reviewPictureView;
            private TextView dateView;
            private TextView reviewTextView;
            private TextView likeNumView;

            public ViewHolder(View view) {
                profileView = (ImageView) findViewById(R.id.listview_detail_item_profile);
                ratingBar = (RatingBar) findViewById(R.id.listview_detail_item_ratingbar);
                ratingScore = (TextView) findViewById(R.id.listview_detail_item_score);
                userName = (TextView) findViewById(R.id.listview_detail_item_username);
                nationalityView = (ImageView) findViewById(R.id.listview_detail_item_nationality);
                reviewPictureView = (ImageView) findViewById(R.id.listview_detail_item_img);
                dateView = (TextView) findViewById(R.id.listview_detail_item_date);
                reviewTextView = (TextView) findViewById(R.id.listview_detail_item_review);
                likeNumView = (TextView) findViewById(R.id.listview_detail_item_liked);
            }
        }
    }

////////////////////  Model
    public class SearchListReviewViewItem {
        private Drawable profileDrawable;
        private Drawable nationality;
        private Drawable reviewDrawable;
        private String userName;
        private String userReview;
        private float ratingScore;
        private String date;
        private int likeNum;
        private float ratingStar;

        public Drawable getReviewDrawable() {
            return reviewDrawable;
        }
        public void setReviewDrawable(Drawable reviewDrawable) {
            this.reviewDrawable = reviewDrawable;
        }

        public String getDate() {
            return date;
        }
        public void setDate(String date) {
        this.date = date;
    }

        public float getRatingStar() {
                return ratingStar;
        }
        public void setRatingStar(float ratingStar) {
            this.ratingStar = ratingStar;
        }

        public Drawable getProfileDrawable() {
            return profileDrawable;
        }
        public void setProfileDrawable(Drawable profileDrawable) {
            this.profileDrawable = profileDrawable;
        }

        public Drawable getNationality() {
            return nationality;
        }
        public void setNationality(Drawable nationality) {
            this.nationality = nationality;
        }

        public String getUserName() {
            return userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserReview() {
            return userReview;
        }
        public void setUserReview(String userReview) {
            this.userReview = userReview;
        }

        public float getRatingScore() {
            return ratingScore;
        }
        public void setRatingScore(float ratingScore) {
            this.ratingScore = ratingScore;
        }


        public int getLikeNum() {
            return likeNum;
        }
        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }
    }
}
