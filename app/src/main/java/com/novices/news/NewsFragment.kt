package com.novices.news

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.makeramen.roundedimageview.RoundedImageView
import com.novices.news.Util.showToast
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import kotlin.concurrent.thread

class NewsFragment(private var newsType:String) : Fragment() {

    private lateinit var refresh:SwipeRefreshLayout

    private lateinit var RW_news: RecyclerView

    private val newsList = ArrayList<News>()
    private fun refresh() {
        thread {
            val request = Request.Builder().url("http://v.juhe.cn/toutiao/index?type=" + newsType + "&key=" + MyApplication.KAY).build()
            val response = OkHttpClient().newCall(request).execute()
            val json = response.body?.string()
            val newsResponse = Gson().fromJson(json,NewsResponse::class.java)
            if (newsResponse.errorcode !=10012){
                try {
                    val data = newsResponse.result.data
                    newsList.clear()
                    newsList.addAll(data)

                    activity?.runOnUiThread{
                        RW_news.adapter?.notifyDataSetChanged()
                    }
                }catch (e:Exception){
                    activity?.runOnUiThread{
                        "请检查网络接口是否正常".showToast()
                    }

                }






            }
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        RW_news = view.findViewById(R.id.RW_News)
        refresh = view.findViewById(R.id.Refresh_News)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //

        RW_news.layoutManager = LinearLayoutManager(MyApplication.context)
        RW_news.adapter = NewsAdapter(newsList)
        refresh()
        refresh.setColorSchemeColors(Color.parseColor("#03A9F4"))
        refresh.setOnRefreshListener{
            thread {
                Thread.sleep(700)
                activity?.runOnUiThread{
                    refresh()
                    refresh.isRefreshing = false
                }


            }

        }

    }

    inner class NewsAdapter(private val newsList: List<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

        inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.news_title)
            val instruction: TextView = itemView.findViewById(R.id.news_instruction)
            val image: RoundedImageView = itemView.findViewById(R.id.news_image)


        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.news_item_1, parent, false)
            return NewsViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            val news = newsList[position]
            holder.title.text = news.title
            holder.instruction.text = news.author_name
            //加载图片
            Glide.with(MyApplication.context).load(news.thumbnail_pic_s).into(holder.image)
            holder.itemView.setOnClickListener {

                val intent = Intent(MyApplication.context,DetailActivity::class.java)
                intent.putExtra("url=",newsList[holder.adapterPosition].url)
                startActivity(intent)
                //("点击事件" + holder.adapterPosition).showToast()


            }


        }

        override fun getItemCount(): Int {
            return newsList.size
        }


    }

}