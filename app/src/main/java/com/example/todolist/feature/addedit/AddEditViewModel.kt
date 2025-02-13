package com.example.todolist.feature.addedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TaskRepository
import com.example.todolist.ui.theme.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditViewModel (
    private val repository: TaskRepository,
) : ViewModel() {

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf<String?>(null)
        private set

    private val _uiEvent = Channel<UiEvent> {  }
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddEditEvent){
        when (event){
            is AddEditEvent.TitleChanged -> {
                title = event.title
            }
            is AddEditEvent.BodyChanged -> {
                description = event.description
            }
            is AddEditEvent.StartChanged -> TODO()
            is AddEditEvent.EndChanged -> TODO()
            is AddEditEvent.Save ->{
                save()
            }
        }
    }

    private fun save() {
        viewModelScope.launch {
            if(title.isBlank()){
                _uiEvent.send(UiEvent.ShowSnackbar("Title cannot be empty"))
                return@launch
            }

            repository.insert(title, description)
            _uiEvent.send(UiEvent.NavigateBack)
        }

    }

    /*
    private fun save() {
        viewModelScope.launch {
            if (title.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar("Title cannot be empty"))
                return@launch
            }

            repository.insert(title, description)

            // Verificando se a inserção ocorreu corretamente
            repository.getAll().collect { tasks ->
                tasks.forEach { task ->
                    println("Tarefa salva: ID=${task.id}, Título=${task.title}, Descrição=${task.body}")
                }
            }

            _uiEvent.send(UiEvent.NavigateBack)
        }
    }
    */

}

