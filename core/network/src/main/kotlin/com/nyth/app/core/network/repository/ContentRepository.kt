package com.nyth.app.core.network.repository

import com.nyth.app.core.model.ext.StringExt.empty
import com.nyth.app.core.model.remote.request.ContactFormRequest
import com.nyth.app.core.model.remote.request.NewsletterSubscribeRequest
import com.nyth.app.core.model.remote.request.PostAccountCourse
import com.nyth.app.core.model.remote.request.SetEmojiPointRequest
import com.nyth.app.core.network.client.ContentClient
import com.nyth.app.core.network.utils.NetworkHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val contentClient: ContentClient,
    private val ioDispatcher: CoroutineDispatcher,
    private val networkHandler: NetworkHandler,
) {
    //region Agreement
    suspend fun getAgreements() =
        networkHandler.handleResponse(request = {
            contentClient.getAgreements()
        }).flowOn(ioDispatcher)

    suspend fun getAgreementVersionIdByAgreementId(
        agreementId: String
    ) =
        networkHandler.handleResponse(request = {
            contentClient.getAgreementVersionIdByAgreementId(agreementId = agreementId)
        }).flowOn(ioDispatcher)

    suspend fun getAgreementContent(
        agreementId: String,
        agreementVersionId: String
    ) =
        networkHandler.handleResponse(request = {
            contentClient.getAgreementContent(
                agreementId = agreementId,
                agreementVersionId = agreementVersionId
            )
        }).flowOn(ioDispatcher)

    suspend fun getAgreementsActive(
        agreementType: String
    ) = networkHandler.handleResponse(request = {
        contentClient.getAgreementsActive(agreementType = agreementType)
    }).flowOn(ioDispatcher)
    // endregion

    //region Slider
    suspend fun getSlider(sliderPosition: String) =
        networkHandler.handleResponse(request = {
            contentClient.getSlider(sliderPosition = sliderPosition)
        }).flowOn(ioDispatcher)
    // endregion

    // region QuestionAndAnswer

    suspend fun getQuestionAndAnswer() = networkHandler.handleResponse(request = {
        contentClient.getQuestionAndAnswer()
    }).flowOn(ioDispatcher)

    suspend fun getQuestionAndAnswerWithModuleType(moduleType: String) =
        networkHandler.handleResponse(request = {
            contentClient.getQuestionAndAnswerWithModuleType(moduleType = moduleType)
        }).flowOn(ioDispatcher)

    // region Course
    suspend fun getCourse(
        courseType: List<String>,
        isFree: Boolean? = null,
        isCertificated: Boolean? = null,
        isUpcoming: Boolean? = null,
        pageSize: Int,
        pageIndex: Int,
        order: String? = null
    ) = networkHandler.handleResponse(
        request = {
            contentClient.getCourse(
                courseType = courseType,
                isFree = isFree,
                isCertificated = isCertificated,
                isUpcoming = isUpcoming,
                pageSize = pageSize,
                pageIndex = pageIndex,
                order = order
            )
        }
    ).flowOn(ioDispatcher)
    // endregion

    // region Course Similar Trainings
    suspend fun getCourseSimilarTrainings(
        referenceCourseId: String,
        pageSize: Int,
        pageIndex: Int,
    ) = networkHandler.handleResponse(
        request = {
            contentClient.getCourseSimilarTrainings(
                referenceCourseId = referenceCourseId,
                pageSize = pageSize,
                pageIndex = pageIndex
            )
        }
    ).flowOn(ioDispatcher)
    // endregion


    // region Course Detail
    suspend fun getCourseByIdDetail(
        courseId: String
    ) = networkHandler.handleResponse(
        request = {
            contentClient.getCourseByIdDetail(courseId = courseId)
        }
    ).flowOn(ioDispatcher)
    // endregion

    // region StudentComments
    suspend fun getCourseStudentComments() = networkHandler.handleResponse(request = {
        contentClient.getCourseStudentComments()
    }).flowOn(ioDispatcher)
    // endregion

    // region Course Detail
    suspend fun getAccountCourses(
        courseType: List<String>,
        isEnded: Boolean? = null,
        isCertificated: Boolean? = null
    ) = networkHandler.handleResponse(
        request = {
            contentClient.getAccountCourses(
                courseType = courseType,
                isEnded = isEnded,
                isCertificated = isCertificated
            )
        }
    ).flowOn(ioDispatcher)
    // endregion

    // region Post AccountCourse
    suspend fun postAccountCourse(postAccountCourse: PostAccountCourse) =
        networkHandler.handleResponse(request = {
            contentClient.postAccountCourse(postAccountCourse)
        }).flowOn(ioDispatcher)
    // endregion

    //region BlogContents
    suspend fun getBlogContentsToday() = networkHandler.handleResponse(request = {
        contentClient.getBlogContentsToday()
    }).flowOn(ioDispatcher)

    suspend fun getBlogContent(id: String) = networkHandler.handleResponse(request = {
        contentClient.getBlogContent(id = id)
    }).flowOn(ioDispatcher)

    suspend fun getBlogContents(
        referenceContentId: String? = null,
        categoryId: String? = null,
        order: String,
        pageSize: Int,
        pageIndex: Int
    ) = networkHandler.handleResponse(request = {
        contentClient.getBlogContents(
            referenceContentId = referenceContentId ?: String.empty,
            categoryId = categoryId,
            order = order,
            pageSize = pageSize,
            pageIndex = pageIndex
        )
    }).flowOn(ioDispatcher)

    suspend fun postSetEmojiPoint(contentId: String, request: SetEmojiPointRequest) =
        networkHandler.handleResponse(request = {
            contentClient.postSetEmojiPoint(contentId = contentId, request = request)
        }).flowOn(ioDispatcher)

    //region BlogCategory
    suspend fun getBlogCategories() = networkHandler.handleResponse(request = {
        contentClient.getBlogCategories()
    }).flowOn(ioDispatcher)


    //region ContactForm
    suspend fun postContactForm(request: ContactFormRequest) =
        networkHandler.handleResponse(request = {
            contentClient.postContactForm(request = request)
        }).flowOn(ioDispatcher)
    //endregion

    //region Newsletter
    suspend fun postSubscribe(request: NewsletterSubscribeRequest) =
        networkHandler.handleResponse(request = {
            contentClient.postSubscribe(request = request)
        }).flowOn(ioDispatcher)

    suspend fun putUnsubscribe(email: String) = networkHandler.handleResponse(request = {
        contentClient.putUnsubscribe(email = email)
    }).flowOn(ioDispatcher)
    //endregion
}