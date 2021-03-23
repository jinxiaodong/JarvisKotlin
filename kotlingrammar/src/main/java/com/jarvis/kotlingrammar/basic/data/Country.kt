package com.jarvis.kotlingrammar.basic.data

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2/19/21
 */
data class Country(val name: String, val continient: String, val population: Int)


class CountryTest {
    fun isBigEuropeanCountry(country: Country): Boolean {
        return country.continient == "EU" && country.population > 10000
    }
}

class CountryApp {

    fun filterCountries(countries: List<Country>): List<Country> {

        val res = mutableListOf<Country>()

        for (c in countries) {
            if (c.continient == "EU") {
                res.add(c)
            }
        }
        return res
    }

    fun filterCountries(countries: List<Country>, continient: String): List<Country> {

        val res = mutableListOf<Country>()

        for (c in countries) {
            if (c.continient == continient) {
                res.add(c)
            }
        }
        return res
    }

    //..... 筛选条件越来越多，那么代码业务高度耦合，
    //使用函数类型 作为参数
    fun filterCountries(countries: List<Country>, test: (Country) -> Boolean): List<Country> {
        val res = mutableListOf<Country>()
        for (c in countries) {
            if (test(c)) {
                res.add(c)
            }
        }
        return res
    }

}