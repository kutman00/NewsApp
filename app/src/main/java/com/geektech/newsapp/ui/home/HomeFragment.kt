package com.geektech.newsapp.ui.home
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geektech.newsapp.App
import com.geektech.newsapp.R
import com.geektech.newsapp.databinding.FragmentHomeBinding
import com.geektech.newsapp.models.News
import com.geektech.newsapp.models.NewsAdapter
import kotlin.properties.Delegates

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: NewsAdapter
    private var changeable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter {
            val news = adapter.getItem(it)
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            val  bundle = Bundle()
            bundle.putSerializable("news", news)
            findNavController().navigate(R.id.navigation_news, bundle)
            changeable = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("Home", "OnViewCreated")
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.navigation_news)
        }
        parentFragmentManager.setFragmentResultListener(
            "rk_news",
            viewLifecycleOwner
        ) { requestKey, bundle ->
            val news = bundle.getSerializable("news") as News
            val position: Int? = null
            if (changeable) {
                position.let {
                    if (it != null) {
                        adapter.replaceItem(news, it)
                    }
                }
            } else {
                adapter.addItem(news)
                binding.recyclerView.adapter = adapter

            }
            binding.recyclerView.adapter = adapter
            alert()
        }


    }
    private fun alert () {
        adapter.onLongClick = {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Delet project list")
            dialog.setMessage("You want to delete project?")
            dialog.setPositiveButton("Yes") { _, _ ->
                val news: News = adapter.getItem(it)
                adapter.deleteItems(it)
                App.database.newsDao().deleteItem(news)
                adapter
            }
                dialog.setNegativeButton("Cancel"){dialog, _-> dialog.cancel()}
                dialog.show()

            }
        }
    }
