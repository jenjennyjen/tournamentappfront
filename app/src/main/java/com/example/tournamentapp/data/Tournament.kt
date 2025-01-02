data class Tournament(
    val id: Int,
    val name: String,
    val startDate: String?,
    val createDate: String?,      // Matches "CreateDate" (nullable)
    val isComplete: Boolean?      // Matches "IsComplete" (nullable)
)
