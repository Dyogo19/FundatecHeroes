import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fundatec.fundatecheroes.character.data.CharacterRequest
import br.com.fundatec.fundatecheroes.character.data.repository.CharacterRepository
import br.com.fundatec.fundatecheroes.character.presentation.model.CharacterViewState
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class CharacterViewModel : ViewModel() {
    private val viewState = MutableLiveData<CharacterViewState>()
    private val repository by lazy { CharacterRepository() }
    val state: LiveData<CharacterViewState> = viewState
    fun validateInputs(name: String?, description: String?,image : String?,universeType : String?,characterType:String?, age: String?, birth_date: String?) {
        var patternAge = Pattern.compile("^(0|[1-9][0-9]*)\$")
        var matcherAge = patternAge.matcher(age)

        var patternBirthDate = Pattern.compile("\\d{2}[-\\/\\.]\\d{2}[-\\/\\.]\\d{4}|\\d{8}")
        var matcherBirthDate = patternBirthDate.matcher(birth_date)

        viewState.value = CharacterViewState.ShowLoading

        if (name.isNullOrBlank() && description.isNullOrBlank() && age.toString()
                .isNullOrBlank() && birth_date.toString().isNullOrBlank()
        ) {
            viewState.value = CharacterViewState.ShowMessageError
            return
        }

        if (name.isNullOrBlank()) {
            viewState.value = CharacterViewState.ShowNameError
            return
        }
        if (description.isNullOrBlank()) {
            viewState.value = CharacterViewState.ShowDescriptionError
            return
        }
        if (!matcherAge.matches()) {
            viewState.value = CharacterViewState.ShowAgeError
            return
        }

        if (age.isNullOrBlank() || age.equals("0")) {
            viewState.value = CharacterViewState.ShowAgeError
            return
        }

        if (!matcherBirthDate.matches()) {
            viewState.value = CharacterViewState.ShowBirthDateError
            return
        }

        if (birth_date.isNullOrBlank()) {
            viewState.value = CharacterViewState.ShowNameError
            return
        }

        if(image.isNullOrBlank()){
            viewState.value = CharacterViewState.ShowImageError
        }

        if(universeType.isNullOrBlank()){
            viewState.value = CharacterViewState.ShowMessageError
        }

        if(characterType.isNullOrBlank()){
            viewState.value = CharacterViewState.ShowMessageError
        }

        createCharacter(name, description,image,universeType,characterType, age, birth_date)
    }

    fun createCharacter(name: String?, description: String?,image: String?,universeType : String?,characterType:String?, age: String?, birthday: String?) {
        viewModelScope.launch {
            try {
                val character = CharacterRequest(
                    name = name ?: "",
                    description = description ?: "",
                    image = image ?: "",
                    universeType = universeType.toString().uppercase() ?:"",
                    characterType = characterType.toString().uppercase() ?:"",
                    age = age?.toIntOrNull() ?: 0,
                    birthday = birthday?: ""
                )
                val response = repository.createCharacter(character)

                if (response) {
                    viewState.value = CharacterViewState.ShowHomeScreen
                } else {
                    viewState.value = CharacterViewState.ShowMessageError
                }
            } catch (e: Exception) {
                viewState.value = CharacterViewState.ShowMessageError
            }
        }
    }

    fun createCharacter(name: String, description: String, image: String, universeType: String) {

    }


}
