package com.nyth.app.core.network.client

import com.nyth.app.core.model.remote.request.ContactFormRequest
import com.nyth.app.core.model.remote.request.NewsletterSubscribeRequest
import com.nyth.app.core.model.remote.request.PostAccountCourse
import com.nyth.app.core.model.remote.request.SetEmojiPointRequest
import com.nyth.app.core.model.remote.response.AccountCourseIdResponse
import com.nyth.app.core.model.remote.response.AccountCourseResponse
import com.nyth.app.core.model.remote.response.AgreementResponse
import com.nyth.app.core.model.remote.response.AgreementsActiveResponse
import com.nyth.app.core.model.remote.response.BaseResponse
import com.nyth.app.core.model.remote.response.BlogCategoriesResponse
import com.nyth.app.core.model.remote.response.BlogContentOfDayResponse
import com.nyth.app.core.model.remote.response.BlogContentResponse
import com.nyth.app.core.model.remote.response.BlogContentsResponse
import com.nyth.app.core.model.remote.response.CourseDetailResponse
import com.nyth.app.core.model.remote.response.CourseResponse
import com.nyth.app.core.model.remote.response.GetAgreementContentResponse
import com.nyth.app.core.model.remote.response.GetAgreementVersionIdByAgreementIdResponse
import com.nyth.app.core.model.remote.response.QuestionAndAnswerResponse
import com.nyth.app.core.model.remote.response.SliderResponse
import com.nyth.app.core.model.remote.response.StudentCommentResponse
import com.nyth.app.core.network.service.ContentService
import retrofit2.Response
import javax.inject.Inject

class ContentClient @Inject constructor(
    private val contentService: ContentService
) {

    suspend fun getAgreements(): Response<BaseResponse<List<AgreementResponse>>> =
        contentService.getAgreements()

    suspend fun getAgreementVersionIdByAgreementId(
        agreementId: String
    ): Response<BaseResponse<GetAgreementVersionIdByAgreementIdResponse>> =
        contentService.getAgreementVersionIdByAgreementId(agreementId = agreementId)

    suspend fun getAgreementContent(
        agreementId: String,
        agreementVersionId: String
    ): Response<BaseResponse<GetAgreementContentResponse>> =
        contentService.getAgreementContent(
            agreementId = agreementId,
            agreementVersionId = agreementVersionId
        )

    suspend fun getAgreementsActive(
        agreementType: String
    ): Response<BaseResponse<AgreementsActiveResponse>> =
        contentService.getAgreementsActive(agreementType = agreementType)

    suspend fun getSlider(sliderPosition: String): Response<BaseResponse<List<SliderResponse>>> =
        contentService.getSlider(sliderPosition = sliderPosition)

    suspend fun getQuestionAndAnswer(): Response<BaseResponse<List<QuestionAndAnswerResponse>>> =
        contentService.getQuestionAndAnswer()

    suspend fun getQuestionAndAnswerWithModuleType(moduleType: String): Response<BaseResponse<List<QuestionAndAnswerResponse>>> =
        contentService.getQuestionAndAnswerWithModuleType(moduleType = moduleType)

    suspend fun getCourse(
        courseType: List<String>,
        isFree: Boolean? = null,
        isCertificated: Boolean? = null,
        isUpcoming: Boolean? = null,
        pageSize: Int,
        pageIndex: Int,
        order: String? = null
    ): Response<BaseResponse<List<CourseResponse>>> =
        contentService.getCourse(
            courseType = courseType,
            isFree = isFree,
            isCertificated = isCertificated,
            isUpcoming = isUpcoming,
            pageSize = pageSize,
            pageIndex = pageIndex,
            order = order
        )

    suspend fun getCourseSimilarTrainings(
        referenceCourseId: String,
        pageSize: Int,
        pageIndex: Int,
    ): Response<BaseResponse<List<CourseResponse>>> =
        contentService.getCourseSimilar(
            referenceCourseId = referenceCourseId,
            pageSize = pageSize,
            pageIndex = pageIndex
        )

    suspend fun getCourseByIdDetail(
        courseId: String
    ): Response<BaseResponse<CourseDetailResponse>> =
        contentService.getCourseByIdDetail(courseId = courseId)

    suspend fun getCourseStudentComments(): Response<BaseResponse<List<StudentCommentResponse>>> =
        contentService.getCourseStudentComments()

    suspend fun getAccountCourses(
        courseType: List<String>,
        isEnded: Boolean? = null,
        isCertificated: Boolean? = null
    ): Response<BaseResponse<List<AccountCourseResponse>>> =
        contentService.getAccountCourses(
            courseType = courseType,
            isEnded = isEnded,
            isCertificated = isCertificated
        )

    suspend fun postAccountCourse(postAccountCourse: PostAccountCourse): Response<BaseResponse<AccountCourseIdResponse>> =
        contentService.postAccountCourse(postAccountCourse)

    suspend fun getBlogCategories(): Response<BaseResponse<List<BlogCategoriesResponse>>> =
        contentService.getBlogCategories()

    suspend fun getBlogContentsToday(): Response<BaseResponse<BlogContentOfDayResponse>> =
        contentService.getBlogContentsToday()

    suspend fun getBlogContent(id: String): Response<BaseResponse<BlogContentResponse>> =
        contentService.getBlogContent(id = id)

    suspend fun getBlogContents(
        referenceContentId: String,
        categoryId: String? = null,
        order: String,
        pageSize: Int,
        pageIndex: Int
    ): Response<BaseResponse<List<BlogContentsResponse>>> =
        contentService.getBlogContents(
            referenceContentId = referenceContentId,
            categoryId = categoryId,
            order = order,
            pageSize = pageSize,
            pageIndex = pageIndex
        )

    suspend fun postSetEmojiPoint(
        contentId: String,
        request: SetEmojiPointRequest
    ): Response<Unit> =
        contentService.postSetEmojiPoint(
            id = contentId,
            request = request
        )

    //region ContactForm
    suspend fun postContactForm(
        request: ContactFormRequest
    ): Response<Unit> = contentService.postContactForm(request = request)
    // endregion

    //region Newsletter
    suspend fun postSubscribe(
        request: NewsletterSubscribeRequest
    ): Response<Unit> = contentService.postSubscribe(
        request = request
    )

    suspend fun putUnsubscribe(
        email: String
    ): Response<Unit> = contentService.putUnsubscribe(email = email)
    //endregion
}