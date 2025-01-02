//import android.content.Context
//import android.net.Uri
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.MultipartBody
//import okhttp3.RequestBody
//import java.io.File
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import com.example.tournamentapp.data.ApiClient
//
//fun prepareFilePart(context: Context, partName: String, fileUri: Uri): MultipartBody.Part {
//    val file = File(context.cacheDir, "upload_image")
//    val inputStream = context.contentResolver.openInputStream(fileUri)
//    file.outputStream().use { outputStream ->
//        inputStream?.copyTo(outputStream)
//    }
//
//    val requestFile = RequestBody.create(
//        context.contentResolver.getType(fileUri)?.toMediaTypeOrNull(),
//        file
//    )
//
//    return MultipartBody.Part.createFormData(partName, file.name, requestFile)
//}
//
//fun uploadTournamentImage(context: Context, tournamentId: Int, fileUri: Uri) {
//    val filePart = prepareFilePart(context, "image", fileUri)
//
//    CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val response = ApiClient.apiService.uploadTournamentImage(tournamentId, filePart)
//            if (response.isSuccessful) {
//                println("Image uploaded successfully!")
//            } else {
//                println("Upload failed: ${response.errorBody()?.string()}")
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//}
