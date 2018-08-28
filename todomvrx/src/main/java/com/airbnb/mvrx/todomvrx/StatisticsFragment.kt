package com.airbnb.mvrx.todomvrx

import android.os.Bundle
import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.todomvrx.core.BaseFragment
import com.airbnb.mvrx.todomvrx.todoapp.R
import com.airbnb.mvrx.todomvrx.util.simpleController
import com.airbnb.mvrx.todomvrx.views.statisticsView
import com.airbnb.mvrx.withState

// todo: remove after https://youtrack.jetbrains.com/issue/KT-26464 is fixed
@SuppressLint("StringFormatMatches")
class StatisticsFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.visibility = View.GONE
    }

    override fun epoxyController() = simpleController(viewModel) { state ->

        if (state.tasks.isEmpty()) {
            statisticsView {
                id("no_tasks")
                statistic(R.string.statistics_no_tasks)
            }
            return@simpleController
        }

        val (completeTasks, activeTasks) = state.tasks.partition { it.complete }
        statisticsView {
            id("active")
            statistic(getString(R.string.statistics_active_tasks, activeTasks.size))
        }
        statisticsView {
            id("complete")
            statistic(getString(R.string.statistics_completed_tasks, completeTasks.size))
        }
    }
}
