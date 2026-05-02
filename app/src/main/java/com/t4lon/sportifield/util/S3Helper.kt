import android.content.Context
import aws.sdk.kotlin.runtime.auth.credentials.DefaultChainCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.DeleteObjectRequest
import aws.sdk.kotlin.services.s3.model.GetObjectRequest
import aws.sdk.kotlin.services.s3.model.PutObjectRequest
import aws.smithy.kotlin.runtime.content.asByteStream
import aws.smithy.kotlin.runtime.content.writeToFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class S3Helper(private val context: Context) {

    private val region = "ap-southeast-1"
    private val bucketName = "ttdatn-media"
    val myCredentialsProvider = DefaultChainCredentialsProvider()

    private suspend fun getS3Client(): S3Client {
        return S3Client.fromEnvironment {
            this.region = this@S3Helper.region
            credentialsProvider = myCredentialsProvider
        }
    }

    suspend fun uploadFile(
        key: String,
        file: File,
        onProgress: ((Double) -> Unit)? = null
    ): Result<String> = withContext(Dispatchers.IO) {
        try {
            getS3Client().use { s3 ->
                val request = PutObjectRequest {
                    bucket = bucketName
                    this.key = key
                    body = file.asByteStream()
                    contentLength = file.length()
                    contentType = getMimeType(file)
                }

                s3.putObject(request)
                Result.success(key)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun downloadFile(
        key: String,
        localFile: File
    ): Result<File> = withContext(Dispatchers.IO) {
        try {
            getS3Client().use { s3 ->
                val request = GetObjectRequest {
                    bucket = bucketName
                    this.key = key
                }

                s3.getObject(request) { response ->
                    response.body?.writeToFile(localFile)
                }
                Result.success(localFile)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteFile(key: String): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            getS3Client().use { s3 ->
                val request = DeleteObjectRequest {
                    bucket = bucketName
                    this.key = key
                }
                s3.deleteObject(request)
                Result.success(true)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun getMimeType(file: File): String {
        return when (file.extension.lowercase()) {
            "jpg", "jpeg" -> "image/jpeg"
            "png" -> "image/png"
            "gif" -> "image/gif"
            "pdf" -> "application/pdf"
            "mp4" -> "video/mp4"
            "mp3" -> "audio/mpeg"
            else -> "application/octet-stream"
        }
    }
}