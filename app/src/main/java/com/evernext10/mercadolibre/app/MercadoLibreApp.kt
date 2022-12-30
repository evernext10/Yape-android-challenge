package com.evernext10.mercadolibre.app

import android.app.Application
import com.evernext10.core.data.di.coreModule
import com.evernext10.marketplace.product.detail.data.di.recipesDetailDataModule
import com.evernext10.marketplace.product.detail.domain.di.recipesDetailDomainModule
import com.evernext10.marketplace.product.detail.presentation.di.recipesDetailPresentationModule
import com.evernext10.marketplace.product.list.data.di.recipesListDataModule
import com.evernext10.marketplace.product.list.domain.di.recipesListDomainModule
import com.evernext10.marketplace.product.list.presentation.di.recipesListPresentationModule
import com.evernext10.navigation.di.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MercadoLibreApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MercadoLibreApp)
            modules(
                listOf(
                    coreModule,
                    recipesListDataModule,
                    recipesListDomainModule,
                    recipesListPresentationModule,
                    navigationModule,
                    recipesDetailDataModule,
                    recipesDetailDomainModule,
                    recipesDetailPresentationModule
                )
            )
        }
    }
}
