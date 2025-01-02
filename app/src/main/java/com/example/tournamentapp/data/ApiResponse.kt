data class ApiResponse<T>(
    val success: Boolean,
    val body: T
)
