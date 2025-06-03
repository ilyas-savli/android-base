package com.nyth.app.core.network.service

import com.nyth.app.core.model.annotation.Authenticated
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
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentService {
    object Endpoint {
        /**
         * Paths
         */
        const val similar = "similar"
        const val courseId = "courseId"
        const val accountCourses = "account-courses"
        const val account_Courses = "account_courses"
        const val studentComments = "student-comments"
        const val agreementId = "agreementId"
        const val agreementVersionId = "agreementVersionId"
        const val today = "today"
        const val contentId = "contentId"
        const val active = "active"
        const val version = "version"
        const val AgreementType = "AgreementType"
        const val subscribe = "subscribe"
        const val unsubscribe = "unsubscribe"
        const val setEmojiPoint = "set-emoji-point"

        /**
         * Fields
         */
        const val sliderPosition = "sliderPosition"
        const val courseType = "CourseType"
        const val referenceCourseId = "ReferenceCourseId"
        const val isFree = "IsFree"
        const val isCertificated = "IsCertificated"
        const val isEnded = "IsEnded"
        const val isUpcoming = "IsUpcoming"
        const val pageIndex = "PageIndex"
        const val pageSize = "PageSize"
        const val order = "Order"
        const val categoryId = "CategoryId"
        const val referenceContentId = "ReferenceContentId"
        const val email = "email"
        const val moduleType = "moduleType"

        object Agreement {
            private const val mainPath = "agreements"
            const val getAgreements = mainPath
            const val getAgreementVersionIdByAgreementId = "${mainPath}/{${agreementId}}/${version}"
            const val getAgreementContent =
                "${mainPath}/{${agreementId}}/${version}/{${agreementVersionId}}"
            const val getAgreementsActive = "${mainPath}/${active}"
        }

        object BlogCategory {
            private const val mainPath = "blog_categories"
            const val blogCategories = mainPath
        }

        object BlogContents {
            private const val mainPath = "blog_contents"
            const val blogContents = mainPath
            const val todayPath = "${mainPath}/${today}"
            const val blogContentsById = "${mainPath}/{${contentId}}"
            const val emoji = "${mainPath}/{${contentId}}/${setEmojiPoint}"
        }

        object Course {
            private const val mainPath = "course"
            private const val detail = "detail"
            const val course = mainPath
            const val courseSimilar = "${mainPath}/${similar}"
            const val courseByIdDetail = "${mainPath}/{${courseId}}"
            const val courseAccountCourses = "${mainPath}/${accountCourses}"
            const val courseGetAccountCourses = "${mainPath}/${account_Courses}"
            const val courseStudentComments = "${mainPath}/${studentComments}"
        }

        object Slider {
            private const val mainPath = "slider"
            const val getSlider = mainPath
        }

        object QuestionAndAnswer {
            private const val mainPath = "question_and_answer"
            const val getQuestionAndAnswer = mainPath
            const val getQuestionAndAnswerWithModuleType = "$mainPath/{$moduleType}"
        }

        object ContactForm {
            private const val mainPath = "contact_form"
            const val postContactFrom = mainPath
        }

        object Newsletter {
            private const val mainPath = "newsletter"
            const val subscribePath = "${mainPath}/${subscribe}"
            const val unsubscribePath = "${mainPath}/${unsubscribe}"
        }
    }

    //region Agreement
    @GET(Endpoint.Agreement.getAgreements)
    suspend fun getAgreements(): Response<BaseResponse<List<AgreementResponse>>>

    @GET(Endpoint.Agreement.getAgreementVersionIdByAgreementId)
    suspend fun getAgreementVersionIdByAgreementId(
        @Path(Endpoint.agreementId) agreementId: String
    ): Response<BaseResponse<GetAgreementVersionIdByAgreementIdResponse>>

    @GET(Endpoint.Agreement.getAgreementContent)
    suspend fun getAgreementContent(
        @Path(Endpoint.agreementId) agreementId: String,
        @Path(Endpoint.agreementVersionId) agreementVersionId: String,
    ): Response<BaseResponse<GetAgreementContentResponse>>

    @GET(Endpoint.Agreement.getAgreementsActive)
    suspend fun getAgreementsActive(
        @Query(Endpoint.AgreementType) agreementType: String
    ): Response<BaseResponse<AgreementsActiveResponse>>
    //endregion

    // region Slider
    @GET(Endpoint.Slider.getSlider)
    suspend fun getSlider(
        @Query(Endpoint.sliderPosition) sliderPosition: String
    ): Response<BaseResponse<List<SliderResponse>>>
    // endregion

    //region QuestionAndAnswer
    @GET(Endpoint.QuestionAndAnswer.getQuestionAndAnswer)
    suspend fun getQuestionAndAnswer(): Response<BaseResponse<List<QuestionAndAnswerResponse>>>

    @GET(Endpoint.QuestionAndAnswer.getQuestionAndAnswerWithModuleType)
    suspend fun getQuestionAndAnswerWithModuleType(@Path(Endpoint.moduleType) moduleType: String): Response<BaseResponse<List<QuestionAndAnswerResponse>>>
    //endregion

    // region Course
    @GET(Endpoint.Course.course)
    suspend fun getCourse(
        @Query(Endpoint.courseType) courseType: List<String>,
        @Query(Endpoint.isFree) isFree: Boolean? = null,
        @Query(Endpoint.isCertificated) isCertificated: Boolean? = null,
        @Query(Endpoint.isUpcoming) isUpcoming: Boolean? = null,
        @Query(Endpoint.pageSize) pageSize: Int,
        @Query(Endpoint.pageIndex) pageIndex: Int,
        @Query(Endpoint.order) order: String? = null
    ): Response<BaseResponse<List<CourseResponse>>>
    // endregion

    // region Course Similar
    @GET(Endpoint.Course.courseSimilar)
    suspend fun getCourseSimilar(
        @Query(Endpoint.referenceCourseId) referenceCourseId: String,
        @Query(Endpoint.pageSize) pageSize: Int,
        @Query(Endpoint.pageIndex) pageIndex: Int,
    ): Response<BaseResponse<List<CourseResponse>>>
    // endregion

    // region Course Detail
    @GET(Endpoint.Course.courseByIdDetail)
    suspend fun getCourseByIdDetail(
        @Path(Endpoint.courseId) courseId: String
    ): Response<BaseResponse<CourseDetailResponse>>
    // endregion

    // region StudentComments
    @GET(Endpoint.Course.courseStudentComments)
    suspend fun getCourseStudentComments(): Response<BaseResponse<List<StudentCommentResponse>>>
    // endregion

    // region get AccountCourse
    @Authenticated
    @GET(Endpoint.Course.courseGetAccountCourses)
    suspend fun getAccountCourses(
        @Query(Endpoint.courseType) courseType: List<String>,
        @Query(Endpoint.isEnded) isEnded: Boolean? = null,
        @Query(Endpoint.isCertificated) isCertificated: Boolean? = null
    ): Response<BaseResponse<List<AccountCourseResponse>>>
    // endregion

    // region post AccountCourse
    @Authenticated
    @POST(Endpoint.Course.courseAccountCourses)
    suspend fun postAccountCourse(
        @Body postAccountCourse: PostAccountCourse
    ): Response<BaseResponse<AccountCourseIdResponse>>
    // endregion

    //region BlogContents
    @GET(Endpoint.BlogContents.todayPath)
    suspend fun getBlogContentsToday(): Response<BaseResponse<BlogContentOfDayResponse>>

    @Authenticated
    @POST(Endpoint.BlogContents.emoji)
    suspend fun postSetEmojiPoint(
        @Path(Endpoint.contentId) id: String,
        @Body request: SetEmojiPointRequest
    ): Response<Unit>

    @GET(Endpoint.BlogContents.blogContentsById)
    suspend fun getBlogContent(
        @Path(Endpoint.contentId) id: String
    ): Response<BaseResponse<BlogContentResponse>>

    @GET(Endpoint.BlogContents.blogContents)
    suspend fun getBlogContents(
        @Query(Endpoint.referenceContentId) referenceContentId: String? = null,
        @Query(Endpoint.categoryId) categoryId: String? = null,
        @Query(Endpoint.order) order: String,
        @Query(Endpoint.pageSize) pageSize: Int,
        @Query(Endpoint.pageIndex) pageIndex: Int
    ): Response<BaseResponse<List<BlogContentsResponse>>>

    //region BlogCategory
    @GET(Endpoint.BlogCategory.blogCategories)
    suspend fun getBlogCategories(): Response<BaseResponse<List<BlogCategoriesResponse>>>


    //region ContactForm
    @POST(Endpoint.ContactForm.postContactFrom)
    suspend fun postContactForm(@Body request: ContactFormRequest): Response<Unit>
    // endregion

    //region Newsletter
    @POST(Endpoint.Newsletter.subscribePath)
    suspend fun postSubscribe(
        @Body request: NewsletterSubscribeRequest
    ): Response<Unit>

    @PUT(Endpoint.Newsletter.unsubscribePath)
    suspend fun putUnsubscribe(@Query(Endpoint.email) email: String): Response<Unit>
    //endregion
}