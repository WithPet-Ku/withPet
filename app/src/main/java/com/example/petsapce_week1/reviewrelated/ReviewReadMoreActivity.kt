package com.example.petsapce_week1.reviewrelated

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petsapce_week1.accommodation.scroll.reviewFragment
import com.example.petsapce_week1.databinding.ActivityReviewReadMoreBinding
import com.example.petsapce_week1.network.RetrofitHelper
import com.example.petsapce_week1.network.ReviewGETAPI
import com.example.petsapce_week1.vo.Review
import com.example.petsapce_week1.vo.ReviewGetData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class ReviewReadMoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReviewReadMoreBinding

    private var retrofit: Retrofit = RetrofitHelper.getRetrofitInstance()
    var api : ReviewGETAPI = retrofit.create(ReviewGETAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReviewReadMoreBinding.inflate(layoutInflater)

        // 왼쪽 상단 x 버튼 클릭 시 이전 화면 => 홈화면 구현 이후 수정
        // (manifest에 parent)
        //android:parentActivityName=""
        binding.btnReviewClose.setOnClickListener {
            val intent = Intent(this, reviewFragment::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)

        //recyclerview 임의로 데이터 추가
        val itemList = ArrayList<ReviewItem>()
        //*val rvAdapter = ReviewAdapter(itemList)

       //* binding.rvReview.adapter = rvAdapter
        //*binding.rvReview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        itemList.add(ReviewItem("3주 전","멍멍이", "너무 좋아요 어쩌고 저쩌고"))
        itemList.add(ReviewItem("4주 전","야옹이", "너무 좋아요 너무 좋아요 Google 지도에서 방문한 장소에 대한 리뷰를 작성할 수 있습니다. 너무 좋아요 Google 지도에서 너무 좋아요 Google 지도에서 너무 좋아요 너무 좋아요 Google 지도에서Google 지도에서 방문한 장소에 대한 리뷰를 작성할 수 있습니다.Google 지도에서 방문한 장소에 대한 리뷰를 작성할 수 있습니다. 블로그 체험단, 인스타그램, 유튜브, 구매리뷰, 기자단, 바이럴 마케팅, 전문 컨설팅, 채널별 최적의 인플루언서가 함께하는 리얼리뷰"))
        itemList.add(ReviewItem("6주 전","집사", "너무 좋아요"))
        itemList.add(ReviewItem("10주 전","댕댕", "너무 좋아요 너무 좋아요 Google 지도에서 방문한 장소에 대한 리뷰를 작성할 수 있습니다."))



        //변경됨을 어댑터에 알림
        //rvAdapter.notifyDataSetChanged()

        loadData()

    }


    private fun setAdapter(itemList : ArrayList<Review>){
        val mAdapter = ReviewAdapter(itemList,this)
        binding.rvReview.adapter = mAdapter
        binding.rvReview.layoutManager = LinearLayoutManager(this)
    }

    private fun loadData() {
        api.getPageofReview(1).enqueue(object : Callback<ReviewGetData> {
            override fun onResponse(call: Call<ReviewGetData>, response: Response<ReviewGetData>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                       //* setAdapter(ReviewGETResult.reviews)
                    }
                }
            }

            override fun onFailure(call: Call<ReviewGetData>, t: Throwable) {
                Log.d("this is error", t.toString())
            }
        })
    }

}