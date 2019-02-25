package shtain.it.studio.dev.countries.test.app.main.countries_list.adapter

import shtain.it.studio.dev.countries.test.app.main.countries_list.models.AdapterData

/**
 * Created by Alex Shtain on 25.02.2019.
 */
interface ClickListener {
    fun itemClicked(data: AdapterData)
}