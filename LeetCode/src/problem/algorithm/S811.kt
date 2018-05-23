package problem.algorithm

class S811 {
    fun subdomainVisits(cpdomains: Array<String>): List<String> {
        val countTable = cpdomains.map { it.substring(0, it.indexOf(" ")).toInt() }.toIntArray()
        val finalMap = mutableMapOf<String, Int>()

        cpdomains.map { it.substring(it.indexOf(" ") + 1) }
                .forEachIndexed { i, str ->
                    val components = str.split(".")
                    components.forEachIndexed { index, _ ->
                        val domain = (index until components.size).joinToString(".") { components[it] }
                        finalMap[domain] = finalMap.getOrDefault(domain, 0) + countTable[i]
                    }
                }

        return finalMap.map { it.value.toString() + " " + it.key }
    }
}