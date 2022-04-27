
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.service.R


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NextButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        enabled = enabled,
        onClick ={},
        colors = ButtonDefaults.buttonColors(backgroundColor = ColorSecondary),
        contentPadding = PaddingValues(14.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.elevation(6.dp),
        content = {
            ConstraintLayout(Modifier.fillMaxWidth()) {
                val (txt, arrow) = createRefs()
                Box(Modifier
                    .constrainAs(arrow) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .alpha(if (enabled) 1f else 0.3f)
                    .background(ColorSecondaryVariant, CircleShape)
                    .padding(6.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_enter_arrow),
                        contentDescription = null,
                        modifier = Modifier.size(10.dp)
                    )
                }
                Text(
                    text = text,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    modifier = Modifier.constrainAs(txt) {
                        centerTo(parent)
                    }
                )
            }
        }, modifier = modifier
    )
}