package br.com.fundatec.fundatecheroes.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import br.com.fundatec.fundatecheroes.character.data.local.CharacterModel
import br.com.fundatec.fundatecheroes.character.heroisRegister.HeroRegisterActivity
import br.com.fundatec.fundatecheroes.character.view.CharacterListAdapter
import br.com.fundatec.fundatecheroes.databinding.ActivityHomeBinding
import br.com.fundatec.fundatecheroes.home.model.HomeViewState
import com.example.module.components.hide
import com.example.module.components.show

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val characterListAdapter by lazy {
        CharacterListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvList.adapter = characterListAdapter

        initializeObserver()
        navigateNewCharacter()
    }

    override fun onResume() {
        super.onResume()
        viewModel.listCharacter()
    }

    private fun initializeObserver() {
        viewModel.state.observe(this) { viewState ->
            when (viewState) {
                is HomeViewState.ShowHomeScreen -> showHomeScreen(viewState.list)
                HomeViewState.ShowEmptyList -> showEmptyList()
                HomeViewState.ShowLoading -> showLoading()
            }
        }
    }

    private fun showHomeScreen(list: List<CharacterModel>) {
        binding.pbLoading.hide()
        characterListAdapter.add(list)
    }

    fun showEmptyList() {
        binding.pbLoading.hide()
    }

    fun showLoading() {
        binding.rvList.show()
    }


    private fun navigateNewCharacter() {
        binding.btHerois.setOnClickListener {

            val intent = Intent(this@HomeActivity, HeroRegisterActivity::class.java)
            startActivity(intent)
        }
    }

}


