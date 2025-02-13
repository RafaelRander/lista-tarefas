@Composable
fun HomeScreen(
    navigateToAddEditScreen: (id: Long?) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()
    
    HomeContent(
        tasks = tasks,
        onAddItemClick = { navigateToAddEditScreen(null) }
    )
}

@Composable
private fun HomeContent(
    tasks: List<Task>,
    onAddItemClick: () -> Unit
) {
    Scaffold(
        // ... existing code ...
    ) {
        LazyColumn {
            // ... existing code ...

            items(tasks) { task ->
                TaskComponent(task = task)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
} 