package UI

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.todolist.databinding.ActivityAddTaskBinding
import com.example.todolist.databinding.ActivityAddTaskBinding.inflate
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ItemTaskBinding.inflate
import com.example.todolist.datasource.TaskDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private val adapter by lazy { TaskListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root))

        binding.rvTasks.adapter = adapter
        updateList()

        insertListeners()
    }

    private fun insertListeners() {
        binding.fab.setOnClikListener {
            ActivityResultContracts.StartActivityForResult(Intent(this, AddTaskActivity::class.java), CREATE_NEW_TASK)
        }

        adapter.listenerEdit = {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            ActivityResultContracts.StartActivityForResult(intent CREATE_NEW_TASK)
        }
        adapter.listenerDelete = {
          TaskDataSource.deleteTask(it)
            updateList()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) {

        }
    }

    companion object{
        private const val CREATE_NEW_TASK = 1000
    }
}
    private fun updateList() {
    adapter.submitList(TaskDataSource.getList())
    }


class ActivityMainBinding {

}
