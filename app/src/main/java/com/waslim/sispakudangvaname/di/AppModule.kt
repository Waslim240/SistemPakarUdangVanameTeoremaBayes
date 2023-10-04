package com.waslim.sispakudangvaname.di

import com.google.firebase.firestore.FirebaseFirestore
import com.waslim.sispakudangvaname.model.repository.daftarpenyakit.DaftarPenyakitRepository
import com.waslim.sispakudangvaname.model.repository.daftarpenyakit.DaftarPenyakitRepositoryImp
import com.waslim.sispakudangvaname.model.repository.diagnosa.earlymortalitysyndrome.EarlyMortalitySyndrome
import com.waslim.sispakudangvaname.model.repository.diagnosa.earlymortalitysyndrome.EarlyMortalitySyndromeImp
import com.waslim.sispakudangvaname.model.repository.diagnosa.enterocytozoonhepatopenaei.EnterocytozoonHepatopenaei
import com.waslim.sispakudangvaname.model.repository.diagnosa.enterocytozoonhepatopenaei.EnterocytozoonHepatopenaeiImp
import com.waslim.sispakudangvaname.model.repository.diagnosa.infectiousmyonecrosis.InfectiousMyoNecrosis
import com.waslim.sispakudangvaname.model.repository.diagnosa.infectiousmyonecrosis.InfectiousMyoNecrosisImpl
import com.waslim.sispakudangvaname.model.repository.diagnosa.vibriosis.Vibriosis
import com.waslim.sispakudangvaname.model.repository.diagnosa.vibriosis.VibriosisImp
import com.waslim.sispakudangvaname.model.repository.diagnosa.whitefecessyndrome.WhiteFecesSyndrome
import com.waslim.sispakudangvaname.model.repository.diagnosa.whitefecessyndrome.WhiteFecesSyndromeImp
import com.waslim.sispakudangvaname.model.repository.diagnosa.whitespotsyndrome.WhiteSpotSyndrome
import com.waslim.sispakudangvaname.model.repository.diagnosa.whitespotsyndrome.WhiteSpotSyndromeImp
import com.waslim.sispakudangvaname.model.repository.gejala.GejalaRepository
import com.waslim.sispakudangvaname.model.repository.gejala.GejalaRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideEarlyMortalitySyndrome(firebaseFirestore: FirebaseFirestore): EarlyMortalitySyndrome {
        return EarlyMortalitySyndromeImp(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideEnterocytozoonHepatopenaei(firebaseFirestore: FirebaseFirestore): EnterocytozoonHepatopenaei {
        return EnterocytozoonHepatopenaeiImp(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideInfectiousMyoNecrosis(firebaseFirestore: FirebaseFirestore): InfectiousMyoNecrosis {
        return InfectiousMyoNecrosisImpl(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideVibriosis(firebaseFirestore: FirebaseFirestore): Vibriosis {
        return VibriosisImp(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideWhiteFacesSyndrome(firebaseFirestore: FirebaseFirestore): WhiteFecesSyndrome {
        return WhiteFecesSyndromeImp(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideWhiteSpotSyndrome(firebaseFirestore: FirebaseFirestore): WhiteSpotSyndrome {
        return WhiteSpotSyndromeImp(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideGejalaRepository(firebaseFirestore: FirebaseFirestore): GejalaRepository {
        return GejalaRepositoryImp(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideDaftarPenyakitRepository(firebaseFirestore: FirebaseFirestore): DaftarPenyakitRepository {
        return DaftarPenyakitRepositoryImp(firebaseFirestore)
    }
}