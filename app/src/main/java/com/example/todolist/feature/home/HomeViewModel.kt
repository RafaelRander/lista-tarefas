@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    
    // Estado para armazenar a lista de tarefas
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collect { taskList ->
                _tasks.value = taskList
            }
        }
    }
} 