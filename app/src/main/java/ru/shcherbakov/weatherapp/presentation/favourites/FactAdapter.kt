package ru.shcherbakov.weatherapp.presentation.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.shcherbakov.weatherapp.R
import ru.shcherbakov.weatherapp.domain.Fact

// Адаптер для RecyclerView, работающий с элементами типа Fact
class FactAdapter: RecyclerView.Adapter<FactAdapter.FactViewHolder>() {

    // Внутренний класс ViewHolder, который хранит ссылки на View элементы
    inner class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Находим TextView в макете элемента списка
        var tvItemFavourites: TextView = itemView.findViewById(R.id.tvItemFavourites)
    }

    // Создаем callback для сравнения элементов списка
    private val callback = object: DiffUtil.ItemCallback<Fact>() {
        // Проверяем, одинаковые ли это элементы (по уникальному идентификатору)
        override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean {
            // Сравниваем по полю link (предполагается, что оно уникально)
            return oldItem.link == newItem.link
        }

        // Проверяем, одинаково ли содержимое элементов
        override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean {
            // Сравниваем все поля (требует, чтобы data class Fact реализовывал equals())
            return oldItem == newItem
        }
    }

    // Создаем AsyncListDiffer для эффективного обновления списка
    val differ = AsyncListDiffer(this, callback)

    // Создает новый ViewHolder (вызывается для каждого элемента при создании)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        // Надуваем макет элемента списка и создаем ViewHolder
        return FactViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_fact, parent, false)
        )
    }

    // Заполняет элемент данными (вызывается для каждого видимого элемента)
    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        // Получаем данные для текущего элемента списка
        val fact = differ.currentList[position]

        // Устанавливаем данные в View элементы
        holder.apply {
            // Устанавливаем текст в TextView
            tvItemFavourites.text = fact.activity
        }
    }

    // Возвращает общее количество элементов в списке
    override fun getItemCount(): Int = differ.currentList.size
}