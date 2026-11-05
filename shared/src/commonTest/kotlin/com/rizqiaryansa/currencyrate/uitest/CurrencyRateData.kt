package com.rizqiaryansa.currencyrate.uitest

import com.rizqiaryansa.currencyrate.presentation.uimodel.CurrencyRateUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

object CurrencyRateData {

    fun getMockData(): ImmutableList<CurrencyRateUiModel> {
        return persistentListOf(
            CurrencyRateUiModel("U", "USD", "United States", "1.0"),
            CurrencyRateUiModel("E", "EUR", "European Union", "0.92"),
            CurrencyRateUiModel("G", "GBP", "United Kingdom", "0.78"),
            CurrencyRateUiModel("S", "SGD", "Singapore", "0.85"),
            CurrencyRateUiModel("I", "IDR", "Indonesia", "0.45")
        )
    }

    fun getCurrencyRateList(): ImmutableList<CurrencyRateUiModel> {
        return persistentListOf(
            CurrencyRateUiModel(
                countryLetter = "A",
                currencyCode = "AFN",
                countryName = "Afghan Afghani",
                rate = "62.749992"
            ), CurrencyRateUiModel(
                countryLetter = "A",
                currencyCode = "ALL",
                countryName = "Albanian Lek",
                rate = "81.555517"
            ), CurrencyRateUiModel(
                countryLetter = "A",
                currencyCode = "DZD",
                countryName = "Algerian Dinar",
                rate = "130.01"
            ), CurrencyRateUiModel(
                countryLetter = "A",
                currencyCode = "AOA",
                countryName = "Angolan Kwanza",
                rate = "912.131"
            ), CurrencyRateUiModel(
                countryLetter = "A",
                currencyCode = "ARS",
                countryName = "Argentine Peso",
                rate = "1375.7733"
            ), CurrencyRateUiModel(
                countryLetter = "A",
                currencyCode = "AMD",
                countryName = "Armenian Dram",
                rate = "374.589061"
            ), CurrencyRateUiModel(
                countryLetter = "A",
                currencyCode = "AWG",
                countryName = "Aruban Florin",
                rate = "1.8"
            ), CurrencyRateUiModel(
                countryLetter = "A",
                currencyCode = "AUD",
                countryName = "Australian Dollar",
                rate = "1.412297"
            ), CurrencyRateUiModel(
                countryLetter = "A",
                currencyCode = "AZN",
                countryName = "Azerbaijani Manat",
                rate = "1.7"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BSD",
                countryName = "Bahamian Dollar",
                rate = "1.0"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BHD",
                countryName = "Bahraini Dinar",
                rate = "0.377025"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BDT",
                countryName = "Bangladeshi Taka",
                rate = "121.593394"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BBD",
                countryName = "Barbadian Dollar",
                rate = "2.0"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BYN",
                countryName = "Belarusian Ruble",
                rate = "2.853229"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BZD",
                countryName = "Belize Dollar",
                rate = "2.001133"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BMD",
                countryName = "Bermudan Dollar",
                rate = "1.0"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BTN",
                countryName = "Bhutanese Ngultrum",
                rate = "90.487301"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BTC",
                countryName = "Bitcoin",
                rate = "1.5162997E-5"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BOB",
                countryName = "Bolivian Boliviano",
                rate = "6.875354"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BAM",
                countryName = "Bosnia - Herzegovina Convertible Mark",
                rate = "1.653772"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BWP",
                countryName = "Botswanan Pula",
                rate = "13.174873"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BRL",
                countryName = "Brazilian Real",
                rate = "5.1858"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "GBP",
                countryName = "British Pound Sterling",
                rate = "0.73933"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BND",
                countryName = "Brunei Dollar",
                rate = "1.262699"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BGN",
                countryName = "Bulgarian Lev",
                rate = "1.654199"
            ), CurrencyRateUiModel(
                countryLetter = "B",
                currencyCode = "BIF",
                countryName = "Burundian Franc",
                rate = "2950.731452"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "XOF",
                countryName = "CFA Franc BCEAO",
                rate = "554.811812"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "XAF",
                countryName = "CFA Franc BEAC",
                rate = "554.811812"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "XPF",
                countryName = "CFP Franc",
                rate = "100.931403"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "KHR",
                countryName = "Cambodian Riel",
                rate = "4001.248037"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "CAD",
                countryName = "Canadian Dollar",
                rate = "1.366893"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "CVE",
                countryName = "Cape Verdean Escudo",
                rate = "93.237122"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "XCG",
                countryName = "Caribbean Guilder",
                rate = "1.793261"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "KYD",
                countryName = "Cayman Islands Dollar",
                rate = "0.829155"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "CLP",
                countryName = "Chilean Peso",
                rate = "862.068966"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "CLF",
                countryName = "Chilean Unit of Account (UF)",
                rate = "0.021864"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "CNY",
                countryName = "Chinese Yuan",
                rate = "6.9088"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "CNH",
                countryName = "Chinese Yuan (Offshore)",
                rate = "6.885511"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "COP",
                countryName = "Colombian Peso",
                rate = "3680.473407"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "KMF",
                countryName = "Comorian Franc",
                rate = "418.000279"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "CDF",
                countryName = "Congolese Franc",
                rate = "2298.939255"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "CRC",
                countryName = "Costa Rican Colón",
                rate = "474.874048"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "HRK",
                countryName = "Croatian Kuna",
                rate = "6.372518"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "CUC",
                countryName = "Cuban Convertible Peso",
                rate = "1.0"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "CUP",
                countryName = "Cuban Peso",
                rate = "25.75"
            ), CurrencyRateUiModel(
                countryLetter = "C",
                currencyCode = "CZK",
                countryName = "Czech Republic Koruna",
                rate = "20.483324"
            ), CurrencyRateUiModel(
                countryLetter = "D",
                currencyCode = "DKK",
                countryName = "Danish Krone",
                rate = "6.319231"
            ), CurrencyRateUiModel(
                countryLetter = "D",
                currencyCode = "DJF",
                countryName = "Djiboutian Franc",
                rate = "177.189802"
            ), CurrencyRateUiModel(
                countryLetter = "D",
                currencyCode = "DOP",
                countryName = "Dominican Peso",
                rate = "61.1603"
            ), CurrencyRateUiModel(
                countryLetter = "E",
                currencyCode = "XCD",
                countryName = "East Caribbean Dollar",
                rate = "2.70255"
            ), CurrencyRateUiModel(
                countryLetter = "E",
                currencyCode = "EGP",
                countryName = "Egyptian Pound",
                rate = "47.8196"
            ), CurrencyRateUiModel(
                countryLetter = "E",
                currencyCode = "ERN",
                countryName = "Eritrean Nakfa",
                rate = "15.0"
            ), CurrencyRateUiModel(
                countryLetter = "E",
                currencyCode = "ETB",
                countryName = "Ethiopian Birr",
                rate = "154.843274"
            ), CurrencyRateUiModel(
                countryLetter = "E",
                currencyCode = "EUR",
                countryName = "Euro",
                rate = "0.845805"
            ), CurrencyRateUiModel(
                countryLetter = "F",
                currencyCode = "FKP",
                countryName = "Falkland Islands Pound",
                rate = "0.73933"
            ), CurrencyRateUiModel(
                countryLetter = "F",
                currencyCode = "FJD",
                countryName = "Fijian Dollar",
                rate = "2.22225"
            ), CurrencyRateUiModel(
                countryLetter = "G",
                currencyCode = "GMD",
                countryName = "Gambian Dalasi",
                rate = "73.500003"
            ), CurrencyRateUiModel(
                countryLetter = "G",
                currencyCode = "GEL",
                countryName = "Georgian Lari",
                rate = "2.675"
            ), CurrencyRateUiModel(
                countryLetter = "G",
                currencyCode = "GHS",
                countryName = "Ghanaian Cedi",
                rate = "10.93495"
            ), CurrencyRateUiModel(
                countryLetter = "G",
                currencyCode = "GIP",
                countryName = "Gibraltar Pound",
                rate = "0.73933"
            ), CurrencyRateUiModel(
                countryLetter = "G",
                currencyCode = "XAU",
                countryName = "Gold Ounce",
                rate = "1.9466E-4"
            ), CurrencyRateUiModel(
                countryLetter = "G",
                currencyCode = "GTQ",
                countryName = "Guatemalan Quetzal",
                rate = "7.634679"
            ), CurrencyRateUiModel(
                countryLetter = "G",
                currencyCode = "GGP",
                countryName = "Guernsey Pound",
                rate = "0.73933"
            ), CurrencyRateUiModel(
                countryLetter = "G",
                currencyCode = "GNF",
                countryName = "Guinean Franc",
                rate = "8729.705551"
            ), CurrencyRateUiModel(
                countryLetter = "G",
                currencyCode = "GYD",
                countryName = "Guyanaese Dollar",
                rate = "208.13784"
            ), CurrencyRateUiModel(
                countryLetter = "H",
                currencyCode = "HTG",
                countryName = "Haitian Gourde",
                rate = "130.421168"
            ), CurrencyRateUiModel(
                countryLetter = "H",
                currencyCode = "HNL",
                countryName = "Honduran Lempira",
                rate = "26.323533"
            ), CurrencyRateUiModel(
                countryLetter = "H",
                currencyCode = "HKD",
                countryName = "Hong Kong Dollar",
                rate = "7.816922"
            ), CurrencyRateUiModel(
                countryLetter = "H",
                currencyCode = "HUF",
                countryName = "Hungarian Forint",
                rate = "321.718764"
            ), CurrencyRateUiModel(
                countryLetter = "I",
                currencyCode = "ISK",
                countryName = "Icelandic Króna",
                rate = "122.55"
            ), CurrencyRateUiModel(
                countryLetter = "I",
                currencyCode = "INR",
                countryName = "Indian Rupee",
                rate = "90.773707"
            ), CurrencyRateUiModel(
                countryLetter = "I",
                currencyCode = "IDR",
                countryName = "Indonesian Rupiah",
                rate = "16802.274591"
            ), CurrencyRateUiModel(
                countryLetter = "I",
                currencyCode = "IRR",
                countryName = "Iranian Rial",
                rate = "42086.0"
            ), CurrencyRateUiModel(
                countryLetter = "I",
                currencyCode = "IQD",
                countryName = "Iraqi Dinar",
                rate = "1303.515969"
            ), CurrencyRateUiModel(
                countryLetter = "I",
                currencyCode = "ILS",
                countryName = "Israeli New Sheqel",
                rate = "3.113488"
            ), CurrencyRateUiModel(
                countryLetter = "J",
                currencyCode = "JMD",
                countryName = "Jamaican Dollar",
                rate = "155.035802"
            ), CurrencyRateUiModel(
                countryLetter = "J",
                currencyCode = "JPY",
                countryName = "Japanese Yen",
                rate = "154.48423077"
            ), CurrencyRateUiModel(
                countryLetter = "J",
                currencyCode = "JEP",
                countryName = "Jersey Pound",
                rate = "0.73933"
            ), CurrencyRateUiModel(
                countryLetter = "J",
                currencyCode = "JOD",
                countryName = "Jordanian Dinar",
                rate = "0.709"
            ), CurrencyRateUiModel(
                countryLetter = "K",
                currencyCode = "KZT",
                countryName = "Kazakhstani Tenge",
                rate = "496.622175"
            ), CurrencyRateUiModel(
                countryLetter = "K",
                currencyCode = "KES",
                countryName = "Kenyan Shilling",
                rate = "128.95"
            ), CurrencyRateUiModel(
                countryLetter = "K",
                currencyCode = "KWD",
                countryName = "Kuwaiti Dinar",
                rate = "0.30654"
            ), CurrencyRateUiModel(
                countryLetter = "K",
                currencyCode = "KGS",
                countryName = "Kyrgystani Som",
                rate = "87.45"
            ), CurrencyRateUiModel(
                countryLetter = "L",
                currencyCode = "LAK",
                countryName = "Laotian Kip",
                rate = "21321.273375"
            ), CurrencyRateUiModel(
                countryLetter = "L",
                currencyCode = "LBP",
                countryName = "Lebanese Pound",
                rate = "89095.050651"
            ), CurrencyRateUiModel(
                countryLetter = "L",
                currencyCode = "LSL",
                countryName = "Lesotho Loti",
                rate = "16.029595"
            ), CurrencyRateUiModel(
                countryLetter = "L",
                currencyCode = "LRD",
                countryName = "Liberian Dollar",
                rate = "183.582355"
            ), CurrencyRateUiModel(
                countryLetter = "L",
                currencyCode = "LYD",
                countryName = "Libyan Dinar",
                rate = "6.294445"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MOP",
                countryName = "Macanese Pataca",
                rate = "8.009639"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MKD",
                countryName = "Macedonian Denar",
                rate = "52.116016"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MGA",
                countryName = "Malagasy Ariary",
                rate = "4258.290252"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MWK",
                countryName = "Malawian Kwacha",
                rate = "1725.392862"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MYR",
                countryName = "Malaysian Ringgit",
                rate = "3.886"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MVR",
                countryName = "Maldivian Rufiyaa",
                rate = "15.46"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "IMP",
                countryName = "Manx pound",
                rate = "0.73933"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MRU",
                countryName = "Mauritanian Ouguiya",
                rate = "39.840018"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MUR",
                countryName = "Mauritian Rupee",
                rate = "46.210002"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MXN",
                countryName = "Mexican Peso",
                rate = "17.146936"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MDL",
                countryName = "Moldovan Leu",
                rate = "17.064944"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MNT",
                countryName = "Mongolian Tugrik",
                rate = "3569.0"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MAD",
                countryName = "Moroccan Dirham",
                rate = "9.123648"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MZN",
                countryName = "Mozambican Metical",
                rate = "63.904994"
            ), CurrencyRateUiModel(
                countryLetter = "M",
                currencyCode = "MMK",
                countryName = "Myanma Kyat",
                rate = "2099.9"
            ), CurrencyRateUiModel(
                countryLetter = "N",
                currencyCode = "NAD",
                countryName = "Namibian Dollar",
                rate = "16.029595"
            ), CurrencyRateUiModel(
                countryLetter = "N",
                currencyCode = "NPR",
                countryName = "Nepalese Rupee",
                rate = "144.779899"
            ), CurrencyRateUiModel(
                countryLetter = "N",
                currencyCode = "ANG",
                countryName = "Netherlands Antillean Guilder",
                rate = "1.79"
            ), CurrencyRateUiModel(
                countryLetter = "N",
                currencyCode = "TWD",
                countryName = "New Taiwan Dollar",
                rate = "31.439001"
            ), CurrencyRateUiModel(
                countryLetter = "N",
                currencyCode = "NZD",
                countryName = "New Zealand Dollar",
                rate = "1.671514"
            ), CurrencyRateUiModel(
                countryLetter = "N",
                currencyCode = "NIO",
                countryName = "Nicaraguan Córdoba",
                rate = "36.613465"
            ), CurrencyRateUiModel(
                countryLetter = "N",
                currencyCode = "NGN",
                countryName = "Nigerian Naira",
                rate = "1341.2"
            ), CurrencyRateUiModel(
                countryLetter = "N",
                currencyCode = "KPW",
                countryName = "North Korean Won",
                rate = "900.0"
            ), CurrencyRateUiModel(
                countryLetter = "N",
                currencyCode = "NOK",
                countryName = "Norwegian Krone",
                rate = "9.527562"
            ), CurrencyRateUiModel(
                countryLetter = "O",
                currencyCode = "OMR",
                countryName = "Omani Rial",
                rate = "0.384501"
            ), CurrencyRateUiModel(
                countryLetter = "P",
                currencyCode = "PKR",
                countryName = "Pakistani Rupee",
                rate = "278.079673"
            ), CurrencyRateUiModel(
                countryLetter = "P",
                currencyCode = "XPD",
                countryName = "Palladium Ounce",
                rate = "5.8954E-4"
            ), CurrencyRateUiModel(
                countryLetter = "P",
                currencyCode = "PAB",
                countryName = "Panamanian Balboa",
                rate = "1.0"
            ), CurrencyRateUiModel(
                countryLetter = "P",
                currencyCode = "PGK",
                countryName = "Papua New Guinean Kina",
                rate = "4.339499"
            ), CurrencyRateUiModel(
                countryLetter = "P",
                currencyCode = "PYG",
                countryName = "Paraguayan Guarani",
                rate = "6433.141931"
            ), CurrencyRateUiModel(
                countryLetter = "P",
                currencyCode = "PEN",
                countryName = "Peruvian Nuevo Sol",
                rate = "3.34231"
            ), CurrencyRateUiModel(
                countryLetter = "P",
                currencyCode = "PHP",
                countryName = "Philippine Peso",
                rate = "57.542997"
            ), CurrencyRateUiModel(
                countryLetter = "P",
                currencyCode = "XPT",
                countryName = "Platinum Ounce",
                rate = "4.9442E-4"
            ), CurrencyRateUiModel(
                countryLetter = "P",
                currencyCode = "PLN",
                countryName = "Polish Zloty",
                rate = "3.571092"
            ), CurrencyRateUiModel(
                countryLetter = "Q",
                currencyCode = "QAR",
                countryName = "Qatari Rial",
                rate = "3.626747"
            ), CurrencyRateUiModel(
                countryLetter = "R",
                currencyCode = "RON",
                countryName = "Romanian Leu",
                rate = "4.3116"
            ), CurrencyRateUiModel(
                countryLetter = "R",
                currencyCode = "RUB",
                countryName = "Russian Ruble",
                rate = "76.869831"
            ), CurrencyRateUiModel(
                countryLetter = "R",
                currencyCode = "RWF",
                countryName = "Rwandan Franc",
                rate = "1453.205044"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SHP",
                countryName = "Saint Helena Pound",
                rate = "0.73933"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SVC",
                countryName = "Salvadoran Colón",
                rate = "8.705872"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "WST",
                countryName = "Samoan Tala",
                rate = "2.72629"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SAR",
                countryName = "Saudi Riyal",
                rate = "3.751195"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "RSD",
                countryName = "Serbian Dinar",
                rate = "99.317"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SCR",
                countryName = "Seychellois Rupee",
                rate = "14.616314"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SLE",
                countryName = "Sierra Leonean Leone",
                rate = "24.5"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SLL",
                countryName = "Sierra Leonean Leone(Old)",
                rate = "20969.5"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "XAG",
                countryName = "Silver Ounce",
                rate = "0.01197648"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SGD",
                countryName = "Singapore Dollar",
                rate = "1.26438"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SBD",
                countryName = "Solomon Islands Dollar",
                rate = "8.045182"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SOS",
                countryName = "Somali Shilling",
                rate = "567.633669"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "ZAR",
                countryName = "South African Rand",
                rate = "15.991654"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "KRW",
                countryName = "South Korean Won",
                rate = "1440.630794"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SSP",
                countryName = "South Sudanese Pound",
                rate = "130.26"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "XDR",
                countryName = "Special Drawing Rights",
                rate = "0.689818"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "LKR",
                countryName = "Sri Lankan Rupee",
                rate = "307.85124"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SDG",
                countryName = "Sudanese Pound",
                rate = "601.5"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SRD",
                countryName = "Surinamese Dollar",
                rate = "37.6355"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SZL",
                countryName = "Swazi Lilangeni",
                rate = "16.024454"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SEK",
                countryName = "Swedish Krona",
                rate = "9.028093"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "CHF",
                countryName = "Swiss Franc",
                rate = "0.772203"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "SYP",
                countryName = "Syrian Pound",
                rate = "13002.0"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "STN",
                countryName = "São Tomé and Príncipe Dobra",
                rate = "20.716626"
            ), CurrencyRateUiModel(
                countryLetter = "S",
                currencyCode = "STD",
                countryName = "São Tomé and Príncipe Dobra(pre - 2018)",
                rate = "22281.8"
            ), CurrencyRateUiModel(
                countryLetter = "T",
                currencyCode = "TJS",
                countryName = "Tajikistani Somoni",
                rate = "9.427716"
            ), CurrencyRateUiModel(
                countryLetter = "T",
                currencyCode = "TZS",
                countryName = "Tanzanian Shilling",
                rate = "2564.811"
            ), CurrencyRateUiModel(
                countryLetter = "T",
                currencyCode = "THB",
                countryName = "Thai Baht",
                rate = "31.004"
            ), CurrencyRateUiModel(
                countryLetter = "T",
                currencyCode = "TOP",
                countryName = "Tongan Pa anga",
                rate = "2.40776"
            ), CurrencyRateUiModel(
                countryLetter = "T",
                currencyCode = "TTD",
                countryName = "Trinidad and Tobago Dollar",
                rate = "6.734875"
            ), CurrencyRateUiModel(
                countryLetter = "T",
                currencyCode = "TND",
                countryName = "Tunisian Dinar",
                rate = "2.8889"
            ), CurrencyRateUiModel(
                countryLetter = "T",
                currencyCode = "TRY",
                countryName = "Turkish Lira",
                rate = "43.830745"
            ), CurrencyRateUiModel(
                countryLetter = "T",
                currencyCode = "TMT",
                countryName = "Turkmenistani Manat",
                rate = "3.5"
            ), CurrencyRateUiModel(
                countryLetter = "U",
                currencyCode = "UGX",
                countryName = "Ugandan Shilling",
                rate = "3581.860317"
            ), CurrencyRateUiModel(
                countryLetter = "U",
                currencyCode = "UAH",
                countryName = "Ukrainian Hryvnia",
                rate = "43.067405"
            ), CurrencyRateUiModel(
                countryLetter = "U",
                currencyCode = "AED",
                countryName = "United Arab Emirates Dirham",
                rate = "3.6725"
            ), CurrencyRateUiModel(
                countryLetter = "U",
                currencyCode = "USD",
                countryName = "United States Dollar",
                rate = "1.0"
            ), CurrencyRateUiModel(
                countryLetter = "U",
                currencyCode = "UYU",
                countryName = "Uruguayan Peso",
                rate = "38.773743"
            ), CurrencyRateUiModel(
                countryLetter = "U",
                currencyCode = "UZS",
                countryName = "Uzbekistan Som",
                rate = "12151.998818"
            ), CurrencyRateUiModel(
                countryLetter = "V",
                currencyCode = "VUV",
                countryName = "Vanuatu Vatu",
                rate = "119.995"
            ), CurrencyRateUiModel(
                countryLetter = "V",
                currencyCode = "VES",
                countryName = "Venezuelan Bolívar Soberano",
                rate = "401.831374"
            ), CurrencyRateUiModel(
                countryLetter = "V",
                currencyCode = "VND",
                countryName = "Vietnamese Dong",
                rate = "26068.823461"
            ), CurrencyRateUiModel(
                countryLetter = "Y",
                currencyCode = "YER",
                countryName = "Yemeni Rial",
                rate = "238.45001"
            ), CurrencyRateUiModel(
                countryLetter = "Z",
                currencyCode = "ZMW",
                countryName = "Zambian Kwacha",
                rate = "18.840211"
            ), CurrencyRateUiModel(
                countryLetter = "Z",
                currencyCode = "ZWL",
                countryName = "Zimbabwean Dollar",
                rate = "322.0"
            ), CurrencyRateUiModel(
                countryLetter = "Z",
                currencyCode = "ZWG",
                countryName = "Zimbabwean ZiG",
                rate = "25.7689"
            )
        )
    }
}