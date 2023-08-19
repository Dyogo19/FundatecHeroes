package br.com.fundatec.fundatecheroes.characterRegister.view

import androidx.recyclerview.widget.RecyclerView
import br.com.fundatec.fundatecheroes.databinding.CharacterListItemBinding
import br.com.fundatec.fundatecheroes.character.data.local.CharacterModel
import com.bumptech.glide.Glide


class CharacterViewHolder(private val binding: CharacterListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterModel) {
        binding.tvNamePersonagem.text = character.name
        Glide.with(binding.root.context)
            .load(character.image)
            .into(binding.gokuzin)
    }
}