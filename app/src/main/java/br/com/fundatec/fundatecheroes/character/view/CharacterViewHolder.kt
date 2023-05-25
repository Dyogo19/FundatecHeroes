package br.com.fundatec.fundatecheroes.character.view

import androidx.recyclerview.widget.RecyclerView
import br.com.fundatec.fundatecheroes.character.domain.CharacterModel
import br.com.fundatec.fundatecheroes.databinding.CharacterListItemBinding

class CharacterViewHolder(private val binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterModel) {
        binding.tvNamePersonagem.text = character.name
    }

}