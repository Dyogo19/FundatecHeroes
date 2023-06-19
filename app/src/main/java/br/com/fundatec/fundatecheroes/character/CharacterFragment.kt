package br.com.fundatec.fundatecheroes.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fundatec.fundatecheroes.character.domain.CharacterModel
import br.com.fundatec.fundatecheroes.character.view.CharacterListAdapter
import br.com.fundatec.fundatecheroes.databinding.FragmentCharacterBinding

private const val ARG_PARAM1 = "param1"

class CharacterFragment : Fragment() {

    private lateinit var binding: FragmentCharacterBinding

    private val adapter by lazy { CharacterListAdapter() }
    private val list = listOf(
        CharacterModel("Superman"), CharacterModel("Batman"), CharacterModel("Flash"),
        CharacterModel("Doutor Estranho"), CharacterModel("Cailan"), CharacterModel("Ricardo"),
        CharacterModel("Homem Formiga"), CharacterModel("Homem Aranha"), CharacterModel("Homem de Ferro"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvList.adapter = adapter
        adapter.add(list)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String ) =
            CharacterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}